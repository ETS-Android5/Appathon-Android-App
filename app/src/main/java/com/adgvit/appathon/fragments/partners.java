package com.adgvit.appathon.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.adgvit.appathon.NetworkUtils.NetworkUtils;
import com.adgvit.appathon.R;
import com.adgvit.appathon.activity.MainActivity;
import com.adgvit.appathon.adapter.SpeakerAdapter;
import com.adgvit.appathon.adapter.SponsorsAdapter;
import com.adgvit.appathon.model.SpeakerModel;
import com.adgvit.appathon.model.SponsorsModel;
import com.adgvit.appathon.networkmodels.Speakers;
import com.adgvit.appathon.networkmodels.Track;
import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class partners extends Fragment {
    View view;
    List<SponsorsModel> sponsorsList;
//    List<SpeakerModel> speakerList;
    RecyclerView sponsorRecyclerView,speakerRecyclerView;
    ImageView aboutUs;
    DatabaseReference myref,myref1;
    LottieAnimationView animation;
    ConstraintLayout ui1;
    List<SpeakerModel> speakersList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_partners, container, false);

        sponsorRecyclerView = view.findViewById(R.id.sponsorRecyclerView);
        speakerRecyclerView = view.findViewById(R.id.speakerRecyclerView);
        aboutUs = view.findViewById(R.id.aboutUsButton);
        ui1 = view.findViewById(R.id.partnersConstraintLayout);
        animation = view.findViewById(R.id.partnersAnimationView);

        animation.setVisibility(View.VISIBLE);
        ui1.setVisibility(View.INVISIBLE);

        sponsorsList =new ArrayList<>();
        speakersList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //myref = database.getReference("Partners").child("sponsors");
        //myref1 = database.getReference("Partners").child("speakers");
        addData();
        //adapter1();
        //adapter2();
        onclickListeners();
        return view;
    }
    public void onclickListeners(){
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.aboutUs();
            }
        });
    }
    public void adapter1(List<SponsorsModel> sponsorsList){
        SponsorsAdapter sponsorsAdapter = new SponsorsAdapter(sponsorsList,view.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        sponsorRecyclerView.setLayoutManager(manager);
        sponsorRecyclerView.setAdapter(sponsorsAdapter);
        if (sponsorsList.isEmpty()){
            ui1.setVisibility(View.INVISIBLE);
            animation.setVisibility(View.VISIBLE);

        }
        else {
            ui1.setVisibility(View.VISIBLE);
            animation.pauseAnimation();
            animation.setVisibility(View.INVISIBLE);
        }
    }
    public void adapter2(List<SpeakerModel> speakersList1){
        SpeakerAdapter adapter = new SpeakerAdapter(speakersList1,view.getContext());
        GridLayoutManager manager = new GridLayoutManager(view.getContext(),2);
        manager.setOrientation(RecyclerView.VERTICAL);
        speakerRecyclerView.setLayoutManager(manager);
        speakerRecyclerView.setAdapter(adapter);
        if (speakersList1.isEmpty()){
            ui1.setVisibility(View.INVISIBLE);
            animation.setVisibility(View.VISIBLE);

        }
        else {
            ui1.setVisibility(View.VISIBLE);
            animation.pauseAnimation();
            animation.setVisibility(View.INVISIBLE);
        }
    }
    public void addData(){
        try {
            Call<List<SponsorsModel>> call = NetworkUtils.networkAPI.getSponsors();
            call.enqueue(new Callback<List<SponsorsModel>>() {
                @Override
                public void onResponse(Call<List<SponsorsModel>> call, Response<List<SponsorsModel>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getContext(), "Error : " + response.message().toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        sponsorsList = response.body();
                        adapter1(sponsorsList);
                    }
                }

                @Override
                public void onFailure(Call<List<SponsorsModel>> call, Throwable t) {

                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "Error : " + e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
        }

//        myref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds: snapshot.getChildren()){
//                    SponsorsModel model = ds.getValue(SponsorsModel.class);
//                    sponsorsList.add(model);
//                }
//                adapter1();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        myref1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds:snapshot.getChildren()){
//                    SpeakerModel model = ds.getValue(SpeakerModel.class);
//                    speakerList.add(model);
//                }
//                adapter2();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        try {
            Call<List<SpeakerModel>> call = NetworkUtils.networkAPI.getSpeakers();
            call.enqueue(new Callback<List<SpeakerModel>>() {
                @Override
                public void onResponse(Call<List<SpeakerModel>> call, Response<List<SpeakerModel>> response) {
                    if(!response.isSuccessful())
                    {
                        Toast.makeText(getContext(), "Error : " + response.message().toString(), Toast.LENGTH_SHORT).show();
                    }
                    speakersList = response.body();
                    System.out.println("Size of Speaker List : " + speakersList.size());
                    adapter2(speakersList);
                }

                @Override
                public void onFailure(Call<List<SpeakerModel>> call, Throwable t) {

                }
            });
        }catch (Exception e)
        {
            Toast.makeText(getContext(),"Error : " + e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }
}