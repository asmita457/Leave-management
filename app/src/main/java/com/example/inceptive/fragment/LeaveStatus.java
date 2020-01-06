package com.example.inceptive.fragment;



import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
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
import com.example.inceptive.navigation.ModelStatus;
import com.example.inceptive.navigation.PrefTag;
import com.example.inceptive.navigation.R;
import com.example.inceptive.navigation.StatusAdapter;
import com.example.inceptive.rest.Utils;
import com.example.inceptive.rest.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeaveStatus extends Fragment {

    View v;
    FrameLayout leavestatus_frame;
    LinearLayout leavestatus_linearLayout;
    RelativeLayout leavestatus_relativeLayout;
    TableLayout leavestatus_tableLayout;
    TextView leavestatus_fromDate,approvedby,leavestatus_toDate,leavestatus_status,textreason,leave_status1,reasonstatus;
    TextView leavestatus_toDate1,leavestatus_status1,leavestatus_fromDate2,leavestatus_toDate2,leavestatus_status2;
    EditText searchedittext;
    ImageView statusimg;

    RecyclerView recycler_view_status;

    StatusAdapter sAdapter;
    ArrayList<ModelStatus> statuslist = new ArrayList<>();

    String from_date,fromdate2,todate1,todate2,leave_status,status2,reason;
    private String leave_type_id;

    public LeaveStatus() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (v == null) {
            v = inflater.inflate(R.layout.status_recycler_view, container, false);
            initialize();
        }
        return v;


    }

    private void initialize()
    {
        recycler_view_status = (RecyclerView)v.findViewById(R.id.recycler_view_status);
        RecyclerView.LayoutManager llm1= new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler_view_status.setLayoutManager(llm1);



        sAdapter = new StatusAdapter(getActivity(), statuslist);
        recycler_view_status.setAdapter(sAdapter);
        statusimg=(ImageView)v.findViewById(R.id.statusimg);

        GetEmployeeData();
        statusimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ApplyLeave applyLeave = new ApplyLeave();
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.emphome_content_frame, applyLeave, FragTags.APPLY_LEAVE).addToBackStack(FragTags.APPLY_LEAVE).commit();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof HeaderApp) {
            ((HeaderApp) getActivity()).HeaderName("Leave Status");
        }
    }

    private void GetEmployeeData() {

        Log.e("LeaveStatus ","inside GetEmployeeData ") ;

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urls.EMPLOYEE_STATUS_URL+"employee_id"+"="+ Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_ID),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            Log.e("LeaveStatus " , "GetEmployeeData  "+response );

                            JSONObject jObj = new JSONObject(response.toString());
                            String token= Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_TOKEN);
                            if (jObj.getString("status").equals("200")) {
                                if (jObj.getString("status_message").equals("Unauthorized User"))

                                {
                                    Toast.makeText(getActivity(),"Session time out ! Please try again",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getActivity(), EmployeeLogin.class);
                                    startActivity(intent);

                                } else if (jObj.getString("status_message").equals("Success")) {


                                    JSONArray array = jObj.getJSONArray("data");

                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject jsonObject = array.getJSONObject(i);

                                        ModelStatus item = new ModelStatus();
                                        String name1=Utils.getStringPreferences(getActivity(),PrefTag.FIRST_NAME);

                                        item.from_date = jsonObject.getString("from_date");
                                        item.to_date = jsonObject.getString("to_date");
                                        item.reason = jsonObject.getString("reason");
                                        item.leave_type = jsonObject.getString("leave_status");
                                        item.approved_by=jsonObject.getString("approved_by");

                                        statuslist.add(item);
                                    }

                                    sAdapter.notifyDataSetChanged();
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



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
    }
