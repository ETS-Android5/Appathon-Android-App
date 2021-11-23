package com.adgvit.appathon.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.adgvit.appathon.R;
import com.adgvit.appathon.fragments.Tracks;
import com.adgvit.appathon.fragments.faq;
import com.adgvit.appathon.fragments.partners;
import com.adgvit.appathon.fragments.timeline;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {
    private SmoothBottomBar smoothBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        smoothBottomBar = (SmoothBottomBar) findViewById(R.id.bottomBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new timeline()).commit();
        smoothBottomBar.setOnItemSelectedListener((OnItemSelectedListener) i -> {
            Fragment selectedFragment = null;
            switch (i){
                case 0:
                    selectedFragment = new timeline();
                    break;
                case 1:
                    selectedFragment = new partners();
                    break;
                case 2:
                    selectedFragment = new Tracks();
                    break;
                case 3:
                    selectedFragment = new faq();
                    break;
            }

            //Frag Transaction
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedFragment).commit();
            return true;
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new timeline()).commit();

    }
}