package com.adgvit.appathon.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.adgvit.appathon.R;
import com.adgvit.appathon.fragments.Tracks;
import com.adgvit.appathon.fragments.faq;
import com.adgvit.appathon.fragments.partners;
import com.adgvit.appathon.fragments.timeline;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {
    private SmoothBottomBar smoothBottomBar;

    private ImageView Start;
    private ConstraintLayout welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences.Editor editor = getSharedPreferences("com.adgvit.appathon", MODE_PRIVATE).edit();
        Start = findViewById(R.id.startbtn);
        welcome = findViewById(R.id.welcome);
        SharedPreferences sp= getSharedPreferences("com.adgvit.appathon", MODE_PRIVATE);
        String token = sp.getString("token", "");
        if (token.equals("")){
            editor.putString("token", "token");
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
            bottomSheetDialog.setContentView(R.layout.welcome_page);
            bottomSheetDialog.setCanceledOnTouchOutside(true);

            //BottomSheetBehavior.from(bottomSheetDialog.findViewById(R.id.)).setDraggable(false);

            bottomSheetDialog.show();
        }
//        Start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                welcome.setVisibility(View.INVISIBLE);
//            }
//        });
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