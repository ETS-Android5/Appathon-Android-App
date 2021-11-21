package com.adgvit.appathon.NetworkInterface;

import com.adgvit.appathon.model.SpeakerModel;
import com.adgvit.appathon.model.faqModel;
import com.adgvit.appathon.networkmodels.Speakers;
import com.adgvit.appathon.networkmodels.Track;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkAPI {

    @GET("details/tracks")
    Call<List<Track>> getTrack();

    @GET("details/speakers")
    Call<List<SpeakerModel>> getSpeakers();

    @GET("details/faq")
    Call<List<faqModel>> getFaq();
}
