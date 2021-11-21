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

import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {
//    NavController navController;
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        SmoothBottomBar bottomBar;
//        navController = new NavController(getApplicationContext());
//        bottomBar = findViewById(R.id.nav_view);
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu,menu);
//        bottomBar.setupWithNavController(menu,navController);
//        return true;
//    }

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new timeline()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedfragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_timeline:
                        selectedfragment = new timeline();
                        break;
                    case R.id.navigation_partners:
                        selectedfragment = new partners();
                        break;
                    case R.id.navigation_tracks:
                        selectedfragment = new Tracks();
                        break;
                    case R.id.navigation_faq:
                        selectedfragment = new faq();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedfragment).commit();
                return  true;
            }
        });


    }
}