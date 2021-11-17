package com.adgvit.appathon.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adgvit.appathon.R;
import com.adgvit.appathon.adapter.trackAdapter;
import com.adgvit.appathon.model.trackDomain;

import java.util.ArrayList;

public class Tracks extends Fragment {

    private RecyclerView recyclerView1;
    private ArrayList<trackDomain> courseModelArrayList;
    View view;


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
        courseModelArrayList = new ArrayList<>();
        recyclerView1 = view.findViewById(R.id.recyclerview);
        courseModelArrayList.add(new trackDomain("Track 1", "Substantial Development", "About Hello, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. About Hello, Lorem ipsum dolor sit amet."));
        courseModelArrayList.add(new trackDomain("Track 2", "Substantial Development", "About Hello, Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. About Hello, Lorem ipsum dolor sit amet."));
        adapter();

        return view;
    }



    public void adapter(){
        trackAdapter customAdapter = new trackAdapter(this, courseModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(customAdapter);

    }

}