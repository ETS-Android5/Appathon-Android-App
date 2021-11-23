package com.adgvit.appathon.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.adgvit.appathon.R;
import com.adgvit.appathon.model.timeLineModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class timeLineAdapter extends RecyclerView.Adapter<timeLineAdapter.MyViewHolder> {

    List<timeLineModel> timelineList;
    Context context;

    public timeLineAdapter(List<timeLineModel> timelineList, Context context) {
        this.timelineList = timelineList;
        this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView eventTime,eventName,eventDescription,eventLink,day1date,day2date,day3date;
        ImageView imageTopDone,imageTopNotDone,imageMiddleDone,imageMiddleNotDone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTime = itemView.findViewById(R.id.eventTimeID);
            eventName=itemView.findViewById(R.id.eventNameID);
            eventDescription = itemView.findViewById(R.id.eventIntro);
            eventLink = itemView.findViewById(R.id.eventLink);
            imageTopDone = itemView.findViewById(R.id.timelinetopdone);
            imageTopNotDone = itemView.findViewById(R.id.timelinetopnotdone);
            imageMiddleDone = itemView.findViewById(R.id.timelinemiddledone);
            imageMiddleNotDone = itemView.findViewById(R.id.timelinemiddlenotdone);
            day1date = (TextView)itemView.findViewById(R.id.day1date_timeline);
            day2date = (TextView)itemView.findViewById(R.id.day2date_timeline);
            day3date = (TextView)itemView.findViewById(R.id.day3date_timeline);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.timelinerecycleritem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //timeLineModel model = timelineList.get(position);
        long date1 = timelineList.get(position).getDate();


        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(date1 * 1000L);
//        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        String time = DateFormat.format("HH:mm:ss",cal).toString();

        //String time = timelineList.get(position).getDate().toString().substring(11,19);
//        try {
//            System.out.println("Date : " + date);
//            if (timelineList.get(position).getDay() == 1) {
////                holder.day1date.setText(date);
//            } else if (timelineList.get(position).getDay() == 2) {
//                holder.day2date.setText(date);
//            } else if (timelineList.get(position).getDay() == 3) {
//                holder.day3date.setText(date);
//            }
//        }catch (Exception e)
//        {
//            System.out.println("Error : " + e.getLocalizedMessage());
//            Toast.makeText(context, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//        }

        holder.eventTime.setText(time);
        holder.eventName.setText(timelineList.get(position).getName());
        System.out.println("ListSize : " + timelineList.size());
        holder.eventDescription.setText(timelineList.get(position).getDescription());
        //holder.eventLink.setText(model.getLink());
        boolean onGoing = timelineList.get(position).isOnGoing();
        boolean isCompleted = timelineList.get(position).isCompleted();
        //Log.i("isFirst",isFirst);
        //Log.i("isCompleted",isCompleted);
        if(isCompleted == true && onGoing == true){
            Log.i("Type","Top Done");
            holder.eventName.setTextColor(ContextCompat.getColor(context,R.color.timeline_purple));
            holder.eventTime.setTextColor(ContextCompat.getColor(context,R.color.timeline_purple));
            holder.eventLink.setVisibility(View.VISIBLE);
            holder.eventLink.setEnabled(true);
            holder.imageTopDone.setVisibility(View.VISIBLE);
            holder.imageTopNotDone.setVisibility(View.INVISIBLE);
            holder.imageMiddleDone.setVisibility(View.INVISIBLE);
            holder.imageMiddleNotDone.setVisibility(View.INVISIBLE);
        }
        else if(isCompleted == false && onGoing == true){
            Log.i("Type","Top");
            //holder.eventName.setTextColor(ContextCompat.getColor(context,R.color.timeline_black));
            //holder.eventTime.setTextColor(ContextCompat.getColor(context,R.color.timeline_black));
            holder.eventLink.setVisibility(View.INVISIBLE);
            holder.eventLink.setEnabled(false);
            holder.imageTopNotDone.setVisibility(View.VISIBLE);
            holder.imageTopDone.setVisibility(View.INVISIBLE);
            holder.imageMiddleDone.setVisibility(View.INVISIBLE);
            holder.imageMiddleNotDone.setVisibility(View.INVISIBLE);

        }
        else if(isCompleted == true && onGoing == false){
            Log.i("Type","Middle Done");
            holder.eventName.setTextColor(ContextCompat.getColor(context,R.color.timeline_purple));
            holder.eventTime.setTextColor(ContextCompat.getColor(context,R.color.timeline_purple));
            holder.eventLink.setVisibility(View.VISIBLE);
            holder.eventLink.setEnabled(true);
            holder.imageMiddleDone.setVisibility(View.VISIBLE);
            holder.imageTopNotDone.setVisibility(View.INVISIBLE);
            holder.imageTopDone.setVisibility(View.INVISIBLE);
            holder.imageMiddleNotDone.setVisibility(View.INVISIBLE);
        }
        else if(isCompleted == false && onGoing == false){
            Log.i("Type","Middle");
            //holder.eventName.setTextColor(ContextCompat.getColor(context,R.color.timeline_black));
            //holder.eventTime.setTextColor(ContextCompat.getColor(context,R.color.timeline_black));
            holder.eventLink.setVisibility(View.INVISIBLE);
            holder.eventLink.setEnabled(false);
            holder.imageMiddleNotDone.setVisibility(View.VISIBLE);
            holder.imageMiddleDone.setVisibility(View.INVISIBLE);
            holder.imageTopNotDone.setVisibility(View.INVISIBLE);
            holder.imageTopDone.setVisibility(View.INVISIBLE);
        }
        else {
            Log.i("Type","Else");
        }
    }

    @Override
    public int getItemCount() {
        return timelineList.size();
    }


}
