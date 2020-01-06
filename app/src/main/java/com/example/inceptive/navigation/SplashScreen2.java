package com.example.inceptive.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class SplashScreen2 extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_screen2);


        RelativeLayout activity_splashscreen2=(RelativeLayout)findViewById(R.id.activity_splashscreen2);
//        LinearLayout splash_linearLayout=(LinearLayout)findViewById(R.id.splash_linearLayout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen2.this,EmployeeLogin.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }
//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//        //create a dialog to ask yes no question whether or not the user wants to exit
//
//    }
}
