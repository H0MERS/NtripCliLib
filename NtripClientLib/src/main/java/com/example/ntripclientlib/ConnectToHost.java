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
    private static String _host;
    private static int _port;
    private static String _username;
    private static String _password;
    private static PrintWriter output;
    private static BufferedReader input;
    private static InputStream inputStream;
    private static String TAG = "ConnectToServerSocket";
    private static Context _context;
    public ConnectToHost(Context c, String host, int port, String username, String password)
    {

        _context = c;
        _host = host;
        _port = port;
        _username = username;
        _password = password;
    }

    public static PrintWriter GetPrinterOutput()
    {
        return output;
    }
    public static BufferedReader GetBufferedRInput(){
        return input;
    }

    public static InputStream GetInputStream(){
        return inputStream;
    }

    public static void Connect() {
        Socket socket;
        try {
            socket = new Socket(_host, _port);

            output = new PrintWriter(socket.getOutputStream());
            inputStream = socket.getInputStream();
            input = new BufferedReader(new InputStreamReader(inputStream));

            // Build request message
            String msg = "GET / HTTP/1.0\r\n";
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
