package com.adgvit.appathon.model;

import java.util.List;

public class timeLine {
    List<timeLineModel> data;
    public timeLine()
    {

    }

    public timeLine(List<timeLineModel> data) {
        this.data = data;
    }

    public List<timeLineModel> getData() {
        return data;
    }

    public void setData(List<timeLineModel> data) {
        this.data = data;
    }
}
