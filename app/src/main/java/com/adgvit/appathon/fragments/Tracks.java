package com.adgvit.appathon.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.adgvit.appathon.NetworkInterface.NetworkAPI;
import com.adgvit.appathon.R;
import com.adgvit.appathon.adapter.trackAdapter;
import com.adgvit.appathon.model.trackDomain;
import com.adgvit.appathon.networkmodels.Track;
import com.adgvit.appathon.NetworkUtils.NetworkUtils;
import com.adgvit.appathon.networkmodels.TrackList;
import com.airbnb.lottie.LottieAnimationView;
import java.util.ArrayList;
import java.util.List;
import android.widget.ScrollView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tracks extends Fragment {

    private RecyclerView recyclerView1;
    private ArrayList<Track> courseModelArrayList;
    View view;
    ImageView patience;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       /* setContentView(R.layout.fragment_track);
        recyclerView1 = findViewById(R.id.recyclerview);

        // here we have created new array list and added data to it.
        courseModelArrayList = new ArrayList<>();
        courseModelArrayList.add(new trackDomain("Track 1", "Substantial Development", "About Hello, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. About Hello, Lorem ipsum dolor sit amet."));
        courseModelArrayList.add(new trackDomain("Track 2", "Substantial Development", "About Hello, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. About Hello, Lorem ipsum dolor sit amet."));
        trackAdapter customAdapter = new trackAdapter(this, courseModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(customAdapter);*/

    }

    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_track, container, false);
        patience = view.findViewById(R.id.patienceTracks);
       courseModelArrayList = new ArrayList<>();
        recyclerView1 = view.findViewById(R.id.recyclerview);
//        courseModelArrayList.add(new trackDomain("Track #1", "Substantial Development", "About Hello, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
//                "About Hello, Lorem ipsum dolor sit amet."));
//        courseModelArrayList.add(new trackDomain("Track #2", "Substantial Development", "About Hello, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
//                "About Hello, Lorem ipsum dolor sit amet."));

        try {
            Call<TrackList> call = NetworkUtils.networkAPI.getTrack();
            call.enqueue(new Callback<TrackList>() {
                @Override
                public void onResponse(Call<TrackList> call, Response<TrackList> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getContext(), "Code "+ response.code() + " error "+response.message(),
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    TrackList trackss = response.body();
                    if(trackss.isLive()){
                        courseModelArrayList.addAll(trackss.getData());
                        System.out.println("Size" + courseModelArrayList.size());
                        adapter();
                        patience.setVisibility(View.GONE);
                    }else {
                        patience.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<TrackList> call, Throwable t) {
                    Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            });
        }
        catch (Exception e){
            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }


        return view;
    }



    public void adapter(){
        trackAdapter customAdapter = new trackAdapter(this, courseModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(customAdapter);

    }

}