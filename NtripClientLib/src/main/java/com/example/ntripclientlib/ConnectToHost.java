package com.example.ntripclientlib;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ConnectToHost {
    private String _host;
    private int _port;
    private String _username;
    private String _password;
    private PrintWriter output;
    private BufferedReader input;
    private InputStream inputStream;
    private String TAG = "ConnectToServerSocket";
    private Context _context;
    public ConnectToHost(Context c, String host, int port, String username, String password)
    {

        _context = c;
        _host = host;
        _port = port;
        _username = username;
        _password = password;
    }

    public PrintWriter GetPrinterOutput()
    {
        return output;
    }
    public BufferedReader GetBufferedRInput(){
        return input;
    }

    public InputStream GetInputStream(){
        return inputStream;
    }

    public void GetBaseTations()
    {
        ConnectTo("");
    }

    public void ConnectTo(String baseStation) {
        Socket socket;
        try {
            socket = new Socket(_host, _port);

            output = new PrintWriter(socket.getOutputStream());
            inputStream = socket.getInputStream();
            input = new BufferedReader(new InputStreamReader(inputStream));

            // Build request message
            String msg = "GET /" + baseStation + " HTTP/1.0\r\n";
            msg += "User-Agent: NTRIP client\r\n";
            msg += "Accept: */*\r\nConnection: close\r\n";

            String auth = Base64.getEncoder().encodeToString((_username + ":" + _password).getBytes(StandardCharsets.UTF_8));
            msg += "Authorization: Basic " + auth +  "\r\n"; // This line can be removed if no authorization is needed

            msg += "\r\n";
            new WriteMessageToServer(_context, output).Send(msg);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(_context,e.getMessage(), Toast.LENGTH_LONG);
        }
    }
}
