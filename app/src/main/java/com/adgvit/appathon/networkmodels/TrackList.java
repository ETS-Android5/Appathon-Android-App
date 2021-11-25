package com.adgvit.appathon.networkmodels;

import java.util.List;

public class TrackList {

    List<Track> data;
    boolean isLive;

    public TrackList(List<Track> data, boolean isLive) {
        this.data = data;
        this.isLive = isLive;
    }

    public List<Track> getData() {
        return data;
    }

    public void setData(List<Track> data) {
        this.data = data;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
