package com.example.ntripclientlib;

public interface IReceiveCallbackData {
    Object getData();
    void setData(Object input);

    int getStatus();
    void setStatus(int input);
}
