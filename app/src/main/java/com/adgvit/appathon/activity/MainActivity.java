package com.adgvit.appathon.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    public static CardView aboutUsCardView;
    private ConstraintLayout eventChoose;
    ImageView button,privacy;
    public static Animation fadeIn,fadeOut;
    private static final String CHANNEL_ID = "PushNotifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fadeIn= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        fadeOut= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeout);
        SharedPreferences.Editor editor = getSharedPreferences("com.adgvit.appathon", MODE_PRIVATE).edit();
        Start = findViewById(R.id.startbtn);
        eventChoose = findViewById(R.id.eventChoose);
        aboutUsCardView = findViewById(R.id.aboutUsCardView);
        button = findViewById(R.id.imageView7);
        privacy = findViewById(R.id.imageView8);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("https://fakeyudi.notion.site/Privacy-Policy-f1037b07e1f941dfb3a16ed75738ab02"));
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("https://appathon.adgvit.com/"));
                startActivity(intent);
            }
        });
        SharedPreferences sp= getSharedPreferences("com.adgvit.appathon", MODE_PRIVATE);
        String token = sp.getString("token", "");
        if (token.equals("")){
            editor.putString("token", "token");
            editor.commit();
            Intent i = new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(i);

            //BottomSheetBehavior.from(bottomSheetDialog.findViewById(R.id.)).setDraggable(false);

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
        eventChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutUsCardView.setAnimation(fadeOut);
                aboutUsCardView.setVisibility(View.GONE);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new timeline()).commit();

    }
    public static void aboutUs(){
        aboutUsCardView.setVisibility(View.VISIBLE);
        aboutUsCardView.setAnimation(fadeIn);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (aboutUsCardView.getVisibility()==View.VISIBLE){
            aboutUsCardView.setAnimation(fadeOut);
            aboutUsCardView.setVisibility(View.GONE);
        }else{
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }


}