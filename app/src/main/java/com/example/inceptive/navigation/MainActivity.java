package com.example.inceptive.navigation;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.inceptive.fragment.ManagerHome;
import com.example.inceptive.fragment.Profile;
import com.example.inceptive.fragment.ViewLeave;
import com.example.inceptive.rest.Utils;

//import com.example.inceptive.fragment.ViewLeave;

//import com.example.inceptive.fragment.SettingFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,HeaderApp{

    DrawerLayout drawer_activity_main;
    TextView main_dashboard, main_viewapply, main_profile, main_logout,managerfname,managerlname,manageremail;
    LinearLayout main_linearLayout, main_linearLayout2, main_linearLayout3;
    ImageView main_imageView;
    android.support.v4.app.FragmentManager fragmentManager;
    FrameLayout main_content_frame;
    ImageView imageViewdrawer;
    TextView tvdashboard;
    RelativeLayout main_relativeLayout;

    String[] object = {"Fragment1","Fragment2","Fragment3"};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_imageView = (ImageView) findViewById(R.id.main_imageView);
        LinearLayout main_linearLayout = (LinearLayout) findViewById(R.id.main_linearLayout);
        LinearLayout main_linearLayout2 = (LinearLayout) findViewById(R.id.main_linearLayout2);
        LinearLayout main_linearLayout3 = (LinearLayout) findViewById(R.id.main_linearLayout3);
        RelativeLayout main_relativeLayout = (RelativeLayout) findViewById(R.id.main_relativeLayout);
        FrameLayout main_content_frame = (FrameLayout) findViewById(R.id.main_content_frame);
//        manageremail=(TextView)findViewById(R.id.manageremail);
        drawer_activity_main = (DrawerLayout) findViewById(R.id.drawer_activity_main);
        managerfname=(TextView)findViewById(R.id.managerfname);
//        managerlname=(TextView)findViewById(R.id.managerlname);
        main_dashboard = (TextView) findViewById(R.id.main_dashboard);
        main_viewapply = (TextView) findViewById(R.id.main_viewapply);
        main_profile = (TextView) findViewById(R.id.main_profile);
        tvdashboard = (TextView) findViewById(R.id.tvdashboard);
        manageremail=(TextView)findViewById(R.id.manageremail);
        main_logout = (TextView) findViewById(R.id.main_logout);
        imageViewdrawer = (ImageView) findViewById(R.id.imageViewdrawer);


        imageViewdrawer.setOnClickListener(this);
        main_imageView.setOnClickListener(this);
        main_dashboard.setOnClickListener(this);
        main_viewapply.setOnClickListener(this);
        main_profile.setOnClickListener(this);
        main_logout.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        ManagerHome ff = new ManagerHome();
        String managerfstname= Utils.getStringPreferences(getApplicationContext(),PrefTag.FIRST_NAME);
//        String managerlstname=Utils.getStringPreferences(getApplicationContext(),PrefTag.LAST_NAME);
//        String eml=Utils.getStringPreferences(getApplicationContext(),PrefTag.EMAIL_ID);

        managerfname.setText(managerfstname);
//        managerlname.setText(managerlstname);
//        manageremail.setText(eml);
        fragmentManager.beginTransaction()
                .replace(R.id.main_content_frame, ff)
                .addToBackStack(null)
                .commit();
        getFragmentManager().executePendingTransactions();


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.imageViewdrawer:
                if (drawer_activity_main.isDrawerOpen(Gravity.LEFT)) {
                    drawer_activity_main.closeDrawers();
                } else {
                    drawer_activity_main.openDrawer(Gravity.LEFT);
                }

                break;

                case R.id.main_dashboard:

                    ManagerHome managerhome=new ManagerHome();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                fragmentManager.beginTransaction()
                        .replace(R.id.main_content_frame, managerhome)
                        .addToBackStack(null)
                        .commit();
                    getFragmentManager().executePendingTransactions();
                break;

                case R.id.main_viewapply:

                fragmentManager = getSupportFragmentManager();
                ViewLeave viewLeave = new ViewLeave();
                if (viewLeave != null && viewLeave.isVisible()) {
                    drawer_activity_main.closeDrawers();
                } else {
                    drawer_activity_main.closeDrawers();

                    fragmentManager.beginTransaction()
                            .replace(R.id.main_content_frame, viewLeave)
                            .addToBackStack(null)
                            .commit();
                    getFragmentManager().executePendingTransactions();


                }
                break;

                case R.id.main_profile:

                fragmentManager = getSupportFragmentManager();
                Profile profile = new Profile();
                if (profile != null && profile.isVisible()) {
                    drawer_activity_main.closeDrawers();
                } else {
                    drawer_activity_main.closeDrawers();
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_content_frame, profile)
                            .addToBackStack(null)
                            .commit();
                    getFragmentManager().executePendingTransactions();

                }
                break;


            case R.id.main_logout:
                drawer_activity_main.closeDrawers();
                new Logout(MainActivity.this).LogoutAPI();
                break;


        }
    }

    public void exit(View v){
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    @Override
    public void onBackPressed() {

        if (drawer_activity_main.isDrawerOpen(Gravity.LEFT)) {
            drawer_activity_main.closeDrawers();
        } else {
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 1) {

                Intent intent = new Intent(getApplicationContext(), EmployeeLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                finish();

            } else {
                getSupportFragmentManager().popBackStack();
//                 super.onBackPressed();
            }
        }

    }

    @Override
    public void HeaderName(String headername)
    {
        tvdashboard.setText(headername);
    }
//
//    private class CustomTouchListener implements View.OnTouchListener {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
////                    ((TextView) v).setTextColor(Color.BLACK); // white
//                    break;
//                case MotionEvent.ACTION_CANCEL:
////                    ((TextView) v).setTextColor(Color.BLACK);
//                    break;
//                case MotionEvent.ACTION_UP:
//                    ((TextView) v).setTextColor(Color.BLUE); // lightblack
//                    break;
//            }
//            return false;
//        }
//    }
}