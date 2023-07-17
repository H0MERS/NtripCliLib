package com.example.ntripclientlib;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class ReceiveMessageFromServer {

    private String TAG = "ReceiveMessageFromServer";
    private Context _context;
    private InputStream _inputStream;
    public ReceiveMessageFromServer(Context c, InputStream inputStream)
    {
        _context = c;
        _inputStream = inputStream;
    }
    public byte[] Read(){
        try {

            byte[] dta = new byte[16384];
            int dRead = _inputStream.read(dta,0, dta.length);
            if(dRead != -1) {
                String d = new String(dta, StandardCharsets.UTF_8); //String.valueOf(data).toLowerCase(Locale.ROOT);
                Log.d(TAG,d);
                return dta;
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(_context,"Error: " + e.getMessage(), Toast.LENGTH_LONG);
        }
        return null;
    }
}
