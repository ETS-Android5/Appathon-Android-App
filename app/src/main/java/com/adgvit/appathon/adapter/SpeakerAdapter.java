package com.adgvit.appathon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adgvit.appathon.R;
import com.adgvit.appathon.model.SpeakerModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.MyViewHolder> {
    List<SpeakerModel> speakerList;
    Context mContext;

    public SpeakerAdapter(List<SpeakerModel> speakerList, Context mContext) {
        this.speakerList = speakerList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.speakersrecycleritem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SpeakerModel model = speakerList.get(position);
        holder.speakerName.setText(model.getSpeakerName());
        holder.speakerDesignation.setText(model.getSpeakerDesignation());
        Glide.with(mContext).load(model.getSpeakerImage()).into(holder.speakerImage);

    }

    @Override
    public int getItemCount() {
        return speakerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView speakerName,speakerDesignation;
        ImageView speakerImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            speakerName = itemView.findViewById(R.id.speakerName);
            speakerDesignation = itemView.findViewById(R.id.speakerDesig);
            speakerImage = itemView.findViewById(R.id.speakerImageView);
        }
    }
}
