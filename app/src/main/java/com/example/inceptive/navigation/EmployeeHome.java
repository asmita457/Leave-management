package com.example.inceptive.navigation;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.inceptive.fragment.ApplyLeave;
import com.example.inceptive.fragment.EmployeeHome1;
import com.example.inceptive.fragment.LeaveStatus;
import com.example.inceptive.rest.Utils;

//import com.example.inceptive.fragment.SettingFragment;


public class EmployeeHome extends AppCompatActivity implements View.OnClickListener,HeaderApp {
    LinearLayout homelinearLayout, homelinearLayout2, homelinearLayout3;
    RelativeLayout homerelativeLayout;
    FrameLayout emphome_content_frame;
    DrawerLayout activity_employee_home;
    TextView dashboard, applyleave, leavestatus, logout,tvdashboard,nametext,nmlst,nmtxt,employeeemail;
String emailid;
    ImageView imageViewdrawer;
    android.support.v4.app.FragmentManager fragmentManager;
    private String actionBarTitle;
    View V;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);

        LinearLayout homelinearLayout = (LinearLayout) findViewById(R.id.homelinearLayout);
        LinearLayout homelinearLayout2 = (LinearLayout) findViewById(R.id.homelinearLayout2);
        LinearLayout homelinearLayout3 = (LinearLayout) findViewById(R.id.homelinearLayout3);
        RelativeLayout homerelativeLayout = (RelativeLayout) findViewById(R.id.homerelativeLayout);
        imageViewdrawer = (ImageView) findViewById(R.id.imageViewdrawer);
        tvdashboard=(TextView)findViewById(R.id.tvdashboard);
        activity_employee_home = (DrawerLayout) findViewById(R.id.activity_employee_home);
        emphome_content_frame = (FrameLayout) findViewById(R.id.emphome_content_frame);
        nmlst=(TextView)findViewById(R.id.nmlst);
        nmtxt=(TextView)findViewById(R.id.nmtxt);
//        employeeemail=(TextView)findViewById(R.id.employeeemail);
        dashboard = (TextView) findViewById(R.id.dashboard);
        applyleave = (TextView) findViewById(R.id.applyleave);
        leavestatus = (TextView) findViewById(R.id.leavestatus);
        logout = (TextView) findViewById(R.id.logout);


        fragmentManager = getSupportFragmentManager();
        EmployeeHome1 ff1 = new EmployeeHome1();
        String nm= Utils.getStringPreferences(getApplicationContext(),PrefTag.FIRST_NAME);
       nmlst.setText(Utils.getStringPreferences(getApplicationContext(),PrefTag.LAST_NAME));
        nmtxt.setText(nm);
//String eml=Utils.getStringPreferences(getApplicationContext(),PrefTag.EMAIL_ID);
//        employeeemail.setText(eml);
        fragmentManager.beginTransaction()
                .replace(R.id.emphome_content_frame, ff1)
                .addToBackStack(null)
                .commit();


        imageViewdrawer.setOnClickListener(this);
        dashboard.setOnClickListener(this);
        applyleave.setOnClickListener(this);
        leavestatus.setOnClickListener(this);
        logout.setOnClickListener(this);

//
//        dashboard.setOnTouchListener(new CustomTouchListener());
//        applyleave.setOnTouchListener(new CustomTouchListener());
//        leavestatus.setOnTouchListener(new CustomTouchListener());
//        logout.setOnTouchListener(new CustomTouchListener());


    }

//    private class CustomTouchListener implements View.OnTouchListener {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
////                    ((TextView) v).setTextColor(Color.BLACK); // white
//                    break;
//                case MotionEvent.ACTION_CANCEL:
////                    ((TextView) v).setTextColor(Color.BLACK); // white
//                    break;
//                case MotionEvent.ACTION_UP:
//                    ((TextView) v).setTextColor(Color.BLUE); // lightblack
//                    break;
//            }
//            return false;
//        }
//    } public void exit(View v){
//        finish();
//        android.os.Process.killProcess(android.os.Process.myPid());
//    }

    @Override
    public void onBackPressed() {

            if (activity_employee_home.isDrawerOpen(Gravity.LEFT)) {
                activity_employee_home.closeDrawers();
            } else {
                int count1 = getSupportFragmentManager().getBackStackEntryCount();

                if (count1 == 1) {

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
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.imageViewdrawer:
                if (activity_employee_home.isDrawerOpen(Gravity.LEFT)) {

                    activity_employee_home.closeDrawers();
                } else {
                    activity_employee_home.openDrawer(Gravity.LEFT);
                }

                break;

            case R.id.dashboard:
            fragmentManager = getSupportFragmentManager();
            EmployeeHome1 employeeHome11=new EmployeeHome1();
            if (employeeHome11 != null && employeeHome11.isVisible()) {
                activity_employee_home.closeDrawers();
            } else {
                activity_employee_home.closeDrawers();
                fragmentManager.beginTransaction()
                        .replace(R.id.emphome_content_frame, employeeHome11)
                        .addToBackStack(null)
                        .commit();
            }
                break;


            case R.id.applyleave:
                fragmentManager = getSupportFragmentManager();
                ApplyLeave applyLeave = new ApplyLeave();
                if (applyLeave != null && applyLeave.isVisible()) {
                    activity_employee_home.closeDrawers();
                } else {
                    activity_employee_home.closeDrawers();
                    fragmentManager.beginTransaction()
                            .replace(R.id.emphome_content_frame, applyLeave)
                            .addToBackStack(null)
                            .commit();

                }
                break;


            case R.id.leavestatus:
                fragmentManager = getSupportFragmentManager();
                LeaveStatus leaveStatus = new LeaveStatus();
                if (leaveStatus != null && leaveStatus.isVisible()) {
                    activity_employee_home.closeDrawers();
                } else {
                    activity_employee_home.closeDrawers();
                    fragmentManager.beginTransaction()
                            .replace(R.id.emphome_content_frame, leaveStatus)
                            .addToBackStack(null)
                            .commit();

                }
                break;


            case R.id.logout:
                activity_employee_home.closeDrawers();
                new Logout(EmployeeHome.this).LogoutAPI();
                break;
        }
    }


    @Override
    public void HeaderName(String headername) {
        tvdashboard.setText(headername);
    }
}