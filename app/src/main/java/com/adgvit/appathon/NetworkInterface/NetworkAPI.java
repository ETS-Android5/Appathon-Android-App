package com.adgvit.appathon.NetworkInterface;

import com.adgvit.appathon.model.SpeakerModel;
import com.adgvit.appathon.model.SponsorsModel;
import com.adgvit.appathon.model.faqModel;
import com.adgvit.appathon.model.timeLine;
import com.adgvit.appathon.model.timeLineModel;
import com.adgvit.appathon.networkmodels.Speakers;
import com.adgvit.appathon.networkmodels.Track;
import com.adgvit.appathon.networkmodels.TrackList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkAPI {

    @GET("details/tracks")
    Call<TrackList> getTrack();

    @GET("details/speakers")
    Call<List<SpeakerModel>> getSpeakers();

    @GET("event/")
    Call<timeLine> getEvents();

    @GET("details/sponsor")
    Call<List<SponsorsModel>> getSponsors();

    @GET("details/faq")
    Call<List<faqModel>> getFaq();

}
