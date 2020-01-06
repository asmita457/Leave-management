package com.example.inceptive.fragment;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inceptive.navigation.EmployeeLogin;
import com.example.inceptive.navigation.FragTags;
import com.example.inceptive.navigation.HeaderApp;
import com.example.inceptive.navigation.PrefTag;
import com.example.inceptive.navigation.R;
import com.example.inceptive.rest.Utils;
import com.example.inceptive.rest.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeHome1 extends Fragment implements View.OnClickListener
{


       View v;
       TextView rejectedcount,pendingcount,approvedcount,appliededcount;
       TextView pendingleave,rejectedleave,approvedleave,appliedleave;
       RelativeLayout emphome1_relativeLayout;
    LinearLayout layout11,layout12,layout13;
       CardView cardView,cardView2,cardView3;
     ImageView floatImag;
//FloatingActionButton floatImage;

    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;


    public EmployeeHome1()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (v == null)
        {
            v = inflater.inflate(R.layout.fragment_employee_home1, container, false);
            initialize();
        }
        return v;
    }

    private void initialize()
    {
        emphome1_relativeLayout=(RelativeLayout)v.findViewById(R.id.emphome1_relativeLayout);
        pendingleave=(TextView)v.findViewById(R.id.pendingleave);
        pendingcount=(TextView)v.findViewById(R.id.pendingcount);
        rejectedleave=(TextView)v.findViewById(R.id.rejectedleave);
        rejectedcount=(TextView)v.findViewById(R.id.rejectedcount);
        approvedleave=(TextView)v.findViewById(R.id.approvedleave);
        approvedcount=(TextView)v.findViewById(R.id.approvedcount);
        appliededcount=(TextView)v.findViewById(R.id.appliededcount);
        appliedleave=(TextView)v.findViewById(R.id.appliedleave);
        cardView=(CardView) v.findViewById(R.id.cardView);
        cardView2=(CardView)v.findViewById(R.id.cardView2);
        cardView3=(CardView)v.findViewById(R.id.cardView3);
        floatImag=(ImageView) v.findViewById(R.id.floatImage);

        GetLeaveCount();
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backward);

       floatImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyLeave applyLeave = new ApplyLeave();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.emphome_content_frame, applyLeave, FragTags.APPLY_LEAVE).addToBackStack(FragTags.APPLY_LEAVE).commit();
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof HeaderApp) {
            ((HeaderApp) getActivity()).HeaderName("Home");
        }
    }

    private void GetLeaveCount()
    {
        String id =  Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_ID);
        String user_role = Utils.getStringPreferences(getActivity(),PrefTag.USER_ROLE);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urls.DASHBOARD_URL+"employee_id"+"="+ Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_ID)+"&"+"role"+"="+Utils.getStringPreferences(getActivity(),PrefTag.USER_ROLE),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jObj = new JSONObject(response.toString());
                            if (jObj.getString("status").equals("200"))
                            {
                                if (jObj.getString("status_message").equals("Unauthorized User"))
                                {
                                    Toast.makeText(getActivity(),"Session time out ! Please try again",Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(getActivity(), EmployeeLogin.class);
                                    startActivity(intent);
                                }
                                else if (jObj.getString("status_message").equals("Success")) {

                                    JSONArray ja = jObj.getJSONArray("data");
                                    if (ja != null && ja.length() > 0)
                                    {
                                        int countapprovedLeaves = 0,countrejectedLeaves = 0,countpendingLeaves=0,countappliedLeaves=0;
                                        for (int i = 0; i < ja.length(); i++)
                                        {
                                             JSONObject obj = ja.getJSONObject(i);
                                              Iterator<String> it=obj.keys();
                                              String keys=it.next();
                                            if(keys.equals("approvedLeaves"))
                                            {
                                                countapprovedLeaves = obj.getInt("approvedLeaves");
                                                approvedcount.setText(String.valueOf(countapprovedLeaves));

                                            }
                                             else if(keys.equals("rejectedLeaves"))
                                            {
                                                countrejectedLeaves = obj.getInt("rejectedLeaves");
                                                rejectedcount.setText(String.valueOf(countrejectedLeaves));
                                            }
                                           else if(keys.equals("pendingLeaves"))
                                            {
                                                countpendingLeaves = obj.getInt("pendingLeaves");
                                                pendingcount.setText(String.valueOf(countpendingLeaves));

                                            } else if(keys.equals("appliedleavecount")){
                                                countappliedLeaves = obj.getInt("appliedleavecount");
                                                appliededcount.setText(String.valueOf(countappliedLeaves));


                                        }
                                            else
                                            {

                                            }
                                        }
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();}
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), "volley error", Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization",Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_TOKEN));
                return headers;

            }
        };

        requestQueue.add(stringRequest);

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.floatImage:

                animateFAB();
                break;

        }
    }
    public void callParentMethod(){
        getActivity().onBackPressed();
    }

    public void animateFAB(){

        if(isFabOpen){

            floatImag.startAnimation(rotate_backward);

            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            floatImag.startAnimation(rotate_forward);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }


}

