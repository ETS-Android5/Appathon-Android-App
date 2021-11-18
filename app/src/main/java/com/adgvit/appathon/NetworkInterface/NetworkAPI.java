package com.adgvit.appathon.NetworkInterface;

import com.adgvit.appathon.networkmodels.Track;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkAPI {

    @GET("details/tracks")
    Call<List<Track>> getTrack();
}
