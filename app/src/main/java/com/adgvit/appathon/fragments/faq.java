package com.adgvit.appathon.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adgvit.appathon.NetworkUtils.NetworkUtils;
import com.adgvit.appathon.R;
import com.adgvit.appathon.adapter.faqAdapter;
import com.adgvit.appathon.model.faqModel;
import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class faq extends Fragment {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    View view;
    List<faqModel> list1,searchList;
    EditText searchEditText;
    RecyclerView faq;
    faqAdapter faqAdapter;
    ImageView micButton;
    LottieAnimationView lottieAnimationView;
    ConstraintLayout ui1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_faq, container, false);
        list1 = new ArrayList<>();
        faq = view.findViewById(R.id.faqRecyclerview);
        searchEditText = view.findViewById(R.id.searchEditText);
        micButton = view.findViewById(R.id.micButton1);
        lottieAnimationView = view.findViewById(R.id.faqAnimationView);
        ui1 = view.findViewById(R.id.faqConstraintLayout);

        ui1.setVisibility(View.INVISIBLE);
        lottieAnimationView.setVisibility(View.VISIBLE);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        myref=database.getReference("FAQ");
        loadData();
        addData();
        adapter();
        onclicklisteners();

        return view;
    }
    public void speak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hi speak something");
        try{
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:
                if (resultCode==RESULT_OK && null!=data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    searchEditText.setText(result.get(0));
                }
        }
    }

    public void onclicklisteners(){

        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchList = new ArrayList<>();
                searchList.clear();
                for(faqModel faq1: list1){
                    if(faq1.getQuestion().toLowerCase().contains(s.toString().toLowerCase())){
                        searchList.add(faq1);

                    }
                }
                faqAdapter.filterList(searchList);
            }
        });
    }
    public void addData(){
        //Custom Backend Code for addData()
        try{
            Call<List<faqModel>> call = NetworkUtils.networkAPI.getFaq();
            call.enqueue(new Callback<List<faqModel>>() {
                @Override
                public void onResponse(Call<List<faqModel>> call, Response<List<faqModel>> response) {
                    if(!response.isSuccessful())
                    {
//                        Toast.makeText(getContext(),"Error : " + response.message().toString(),Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        list1 = response.body();
                        saveData();
                        adapter();
                    }

                }

                @Override
                public void onFailure(Call<List<faqModel>> call, Throwable t) {

                }
            });
        }catch (Exception e)
        {
//            Toast.makeText(getContext(),"Error : " + e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
        }


        //Firebase Code
//        myref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                try {
//                    //Log.i("Data","Change1");
//                    list1.clear();
//                    for (DataSnapshot ds : snapshot.getChildren()) {
//                        faqModel faq = ds.getValue(faqModel.class);
//                        list1.add(new faqModel(faq.getQuestion(), faq.getAnswer()));
//                        Log.i(faq.getQuestion(),faq.getAnswer());
//                    }
//                    saveData();
//                    adapter();
//                }
//                catch (Exception e){
//                    //Log.i("Data","Change2");
//                    Toast.makeText(view.getContext(), "Error Occurred Please Try Again", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.i("Data","Cancelled");
//                loadData();
//            }
//        });

    }
    public void saveData(){
        SharedPreferences preferences = view.getContext().getSharedPreferences("com.adgvit.hackgrid.faq", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list1);
        editor.putString("faq",json);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences preferences = view.getContext().getSharedPreferences("com.adgvit.hackgrid.faq",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("faq","");
        Type type = new TypeToken<ArrayList<faqModel>>() {}.getType();
        list1 =gson.fromJson(json,type);
        if(list1==null){
            list1 =new ArrayList<>();
        }
    }
    public void adapter(){
        if (list1.isEmpty()){
            Log.i("Adapter","Empty");
            ui1.setVisibility(View.INVISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);

        }
        else {
            Log.i("Adapter","Not Empty");
            ui1.setVisibility(View.VISIBLE);
            lottieAnimationView.pauseAnimation();
            lottieAnimationView.setVisibility(View.INVISIBLE);

        }
        faqAdapter= new faqAdapter(list1,view.getContext());
        LinearLayoutManager manager =new LinearLayoutManager(view.getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        faq.setLayoutManager(manager);
        faq.setAdapter(faqAdapter);



    }
}