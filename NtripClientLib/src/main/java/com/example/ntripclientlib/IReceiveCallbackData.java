package com.example.ntripclientlib;

public interface IReceiveCallbackData {
    Object getData();
    void setData(Object input);

    boolean getBaseStationIsConnected();
    void setBaseStationIsconnected(boolean input);
}
