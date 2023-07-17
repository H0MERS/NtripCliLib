package com.example.ntripclientlib;

import android.content.Context;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class WriteMessageToServer  {
    private static PrintWriter _output;
    private static Context _context;
    public WriteMessageToServer(Context context,
                                PrintWriter output)
    {
        _context = context;
        _output = output;
    }

    public static void Send(String message)
    {
        _output.write(message);
        _output.flush();
    }
}
