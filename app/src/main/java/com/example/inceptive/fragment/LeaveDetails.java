package com.example.inceptive.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.inceptive.navigation.HeaderApp;
import com.example.inceptive.navigation.R;

public class LeaveDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_details);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getApplicationContext() instanceof HeaderApp) {
            ((HeaderApp) getApplicationContext()).HeaderName("Leave Details");
        }
    }
}
