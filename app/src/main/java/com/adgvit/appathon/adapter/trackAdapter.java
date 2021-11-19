package com.adgvit.appathon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adgvit.appathon.R;
import com.adgvit.appathon.fragments.Tracks;
import com.adgvit.appathon.model.trackDomain;
import com.adgvit.appathon.networkmodels.Track;

import java.util.ArrayList;

public class trackAdapter extends RecyclerView.Adapter<trackAdapter.Viewholder> {

    private Tracks context;
    private ArrayList<Track> courseModelArrayList;

    public trackAdapter(Tracks context, ArrayList<Track> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public trackAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Track model = courseModelArrayList.get(position);
        position = position + 1;
        holder.track.setText("Track#"+ position );
        holder.heading.setText(model.getTitle());
        holder.content.setText(model.getDescription());
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number

        // of card items in recycler view.
        System.out.println("Size "+courseModelArrayList.size());
        return courseModelArrayList.size();

    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView track;
        private TextView heading;
        private TextView content;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            track = itemView.findViewById(R.id.track);
            heading = itemView.findViewById(R.id.heading);
            content = itemView.findViewById(R.id.content);
        }
    }
}
