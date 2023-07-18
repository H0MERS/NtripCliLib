package com.example.ntripclientlib;

public class ModelBase {
    private Object _data;
    private int _status;


    public Object getData() {
        return _data;
    }


    public void setData(Object input) {
        _data = input;
    }


    public int getStatus() {
        return _status;
    }


    public void setStatus(int input) {
        _status = input;
    }
}
