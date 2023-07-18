package com.example.ntripclientlib;

public class ModelBase {
    private Object _data;
    private boolean _isConnectedToBaseStation;


    public Object getData() {
        return _data;
    }


    public void setData(Object input) {
        _data = input;
    }


    public boolean getBaseStationIsConnected() {
        return _isConnectedToBaseStation;
    }


    public void setBaseStationIsconnected(boolean input) {
        _isConnectedToBaseStation = input;
    }
}
