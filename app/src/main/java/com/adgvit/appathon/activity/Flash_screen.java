package com.adgvit.appathon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.adgvit.appathon.R;

public class Flash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);


        Thread thread = new Thread(){

            public void run(){
                try{
                    sleep(2500);

                }
                catch (Exception e){
                    e.printStackTrace();

                }
                finally {
                    Intent intent = new Intent(Flash_screen.this , MainActivity.class);
                    startActivity(intent);

                }
                }

        };thread.start();
    }
}