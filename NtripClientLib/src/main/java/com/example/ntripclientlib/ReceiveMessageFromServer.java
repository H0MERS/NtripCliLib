package com.example.ntripclientlib;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReceiveMessageFromServer implements Runnable{

    private String TAG = "ReceiveMessageFromServer";
    private Context _context;
    private InputStream _inputStream;
    private IReceiveCallback _callbackFunc;
    public ReceiveMessageFromServer(Context c,
                                    InputStream inputStream,
                                    IReceiveCallback callbackFunc)
    {
        _context = c;
        _inputStream = inputStream;
        _callbackFunc = callbackFunc;
    }

    @Override
    public void run() {
        try {
            while(true) {
                byte[] dta = new byte[16384];
                int dRead = _inputStream.read(dta,0, dta.length);
                if(dRead != -1) {
                    String d = new String(dta, StandardCharsets.UTF_8); //String.valueOf(data).toLowerCase(Locale.ROOT);
                    Log.d(TAG,d);

                    String lines[] = d.split("\\r?\\n");
                    List<String> baseStations = new ArrayList<>();
                    if(d.toUpperCase(Locale.ROOT).contains("SOURCETABLE")){
                        for (String l: lines) {
                            if(l.toLowerCase(Locale.ROOT).contains("str")){
                                String[] bStationInf = l.split(";");
                                baseStations.add(bStationInf[1]);
                            }
                        }
                        IReceiveCallbackData m = new ModelSourceTable();
                        m.setData(baseStations);
                        _callbackFunc.callback(m);
                    }else if(d.toLowerCase(Locale.ROOT).contains("icy 200 ok")){
                        IReceiveCallbackData m = new ModelConnectivityStatus();
                        m.setData("ICY 200 OK");
                        m.setBaseStationIsconnected(true);
                        _callbackFunc.callback(m);
                    }else {
                        IReceiveCallbackData m = new ModelCorrectionData();
                        m.setData(dta);
                        m.setBaseStationIsconnected(true);
                        _callbackFunc.callback(m);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(_context,"Error: " + e.getMessage(), Toast.LENGTH_LONG);
        }
    }
}
