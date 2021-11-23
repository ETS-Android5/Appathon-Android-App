package com.adgvit.appathon.networkmodels;

import java.util.List;

public class TrackList {
    public List<Track> getData() {
        return data;
    }

    public void setData(List<Track> data) {
        this.data = data;
    }

    public TrackList(List<Track> data) {
        this.data = data;
    }

    List<Track> data;
}
