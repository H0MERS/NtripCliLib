package com.example.ntripclientlib;

import android.content.Context;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class WriteMessageToServer  {
    private PrintWriter _output;
    private Context _context;
    public WriteMessageToServer(Context context,
                                PrintWriter output)
    {
        _context = context;
        _output = output;
    }

    public void Send(String message)
    {
        _output.write(message);
        _output.flush();
    }
}
