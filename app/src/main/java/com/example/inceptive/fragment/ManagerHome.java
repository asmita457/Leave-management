package com.example.inceptive.fragment;


import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inceptive.navigation.EmployeeLogin;
import com.example.inceptive.navigation.FragTags;
import com.example.inceptive.navigation.HeaderApp;
import com.example.inceptive.navigation.ManagerAdapter;
import com.example.inceptive.navigation.PrefTag;
import com.example.inceptive.navigation.R;
import com.example.inceptive.navigation.SpinnerModel;
import com.example.inceptive.navigation.SplashScreen2;
import com.example.inceptive.navigation.UpdateInter;
import com.example.inceptive.navigation.managerUpdate;
import com.example.inceptive.navigation.viewList;
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
public class ManagerHome extends Fragment implements UpdateInter ,managerUpdate {

    private static final String TAG = " ViewLeave "  ;
    Context ctx;
    View v;

    RecyclerView recycler_view_view;
    RelativeLayout viewleave_relativeLayout;
//    TextView viewleave_chkstatus,status,status1;
//    TableLayout viewleave_tableLayout;
//    Button btnupdate;
//    TextView reason;
    String leave_status_id;
//    TextView counttext;
    String FROM_DATE,TO_DATE;
//    TextView viewleave_empid,viewleave_empid1,man_leaveid;
//    TextView viewleave_fromdate,viewleave_fromdate1;
//    TextView viewleave_todate,viewleave_todate1;
    public ManagerAdapter mAdapter;
    managerUpdate managerupdate;
    UpdateInter updateinter;
    TextView approvedcount,appliededcount,rejectedcount,pendingcount;
    ArrayList<SpinnerModel> Statusleave=new ArrayList<>();
    ArrayList<viewList> viewlist = new ArrayList<>();
//    ArrayList<String> CountryName=new ArrayList<>();
    EditText viewsearch;
ImageView cross;

    SwipeRefreshLayout mSwipeRefreshLayout;


//    Spinner spinnerdrop;
    String URL_LEAVE = "https://inceptivetechnologies.com/intranet/leave_management/api/leaveapi.php/leavestatus";

    int leaveid;
    String leavestatus;
    private String leave_id;
    private String leave_status;


    public ManagerHome()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        if (v == null) {
            v = inflater.inflate(R.layout.view_recycler_view, container, false);
            initialize();
//            getFragmentManager().beginTransaction().replace(R.id.main_content_frame, fragment).addToBackStack("my_fragment").commit();
        }
        return v;

    }


    private void initialize()

    {

        viewleave_relativeLayout=(RelativeLayout)v.findViewById(R.id.viewleave_relativeLayout);
//        approvedcount=(TextView)v.findViewById(R.id.approvedcount);
//        viewleave_fromdate=(TextView)v.findViewById(R.id.viewleave_fromdate);

//        TextView fname=(TextView)v.findViewById(R.id.fname);
//        viewleave_todate=(TextView)v.findViewById(R.id.viewleave_todate);
        viewsearch=(EditText)v.findViewById(R.id.viewsearch);
//        spinnerdrop=(Spinner)v.findViewById(R.id.spinnerdrop);
//        man_leaveid=(TextView)v.findViewById(R.id.man_leaveid);
//        reason=(TextView)v.findViewById(R.id.reason);

        recycler_view_view = (RecyclerView)v.findViewById(R.id.recycler_view_view);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler_view_view.setLayoutManager(llm);

//        viewleave_empid=(TextView)v.findViewById(R.id.fname);

        cross=(ImageView)v.findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewsearch.setText("");

            }
        });
        mAdapter = new ManagerAdapter(getActivity(),viewlist,leave_status_id,FROM_DATE,TO_DATE,Statusleave,managerupdate,this);
        recycler_view_view.setAdapter(mAdapter);

        getViewdata();
        viewsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });

        getSpinnerData();

    }

    private void filter(String text) {


        ArrayList<viewList> filteredList=new ArrayList<>();
        for(viewList item1 : viewlist)
        {
            if(item1.getFirst_name().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item1);
            }

            else if(item1.getLast_name().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item1);
            }
        }

        mAdapter.FilterList(filteredList);

    }
//    @Override
//    public void onBackPressed()
//    {
//
//    }


    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof HeaderApp) {
            ((HeaderApp) getActivity()).HeaderName("Pending Leave");
        }
    }

    private void getSpinnerData() {


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://inceptivetechnologies.com/intranet/leave_management/api/leaveapi.php/leavestatus",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)

                    {

                        System.out.println(response);
                        Log.e(TAG , "response in getSpinnerData "+ response) ;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals("200"))
                            {
                                if (jsonObject.getString("status_message").equals("Unauthorized User"))
                                {

                                    Toast.makeText(getActivity(),"Session time out ! Please try again",Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(ctx, EmployeeLogin.class);
                                    startActivity(intent);

                                }

                                else {

                                    if (jsonObject.getString("status_message").equals("Success")) {
                                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                                        for (int i = 0; i < jsonArray.length(); i++)
                                        {
                                            SpinnerModel model = new SpinnerModel();
                                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                            model.leave_status_id = jsonObject1.getString("leave_status_id");
                                            String lv_id= model.leave_status_id;
                                            Utils.setStringPreferences(getActivity(),PrefTag.LEAVE_ID,lv_id);

                                            model.leave_status = jsonObject1.getString("leave_status");

                                            Statusleave.add(model);

//                                        spinnerdrop.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Statusleave));

                                        }
                                        mAdapter.notifyDataSetChanged();

                                    }
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
                        error.printStackTrace();
                    }
                })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization",Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_TOKEN));
                return headers;

            }
        };
        requestQueue.add(stringRequest);
    }


    public void updateRecord(String leave_type_id,String leave_Id) {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String name=Utils.getStringPreferences(getActivity(),PrefTag.FIRST_NAME);
        Map<String, String> params = new HashMap<String, String>();
        params.put("leave_id",leave_Id);
        params.put("leave_status",leave_type_id);
        params.put("approved_by",name);
        final JSONObject jsonObj = new JSONObject(params);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, urls.UPDATE_URL, jsonObj,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try{
                            JSONObject jObj = new JSONObject(response.toString());
                            if (jObj.getString("status").equals("200"))
                            {
                                Log.e(TAG , " updateRecord response "+ jObj ) ;
                                if(jObj.getString("status_message").equals("Unauthorized User"))
                                {
                                    Toast.makeText(getActivity(),"Session time out ! Please try again",Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(ctx, EmployeeLogin.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    if(jObj.getString("status_message").equals("Success"))
                                    {
                                        JSONArray ja = response.getJSONArray("data");
                                        for(int i=0; i < ja.length(); i++)
                                        {
                                            JSONObject jsonObject = ja.getJSONObject(i);

                                            String leave_id = jsonObject.getString("leave_id");
//                                                Utils.setStringPreferences(getActivity(),PrefTag.LEAVE_ID,leave_id);
                                        }
                                    } else
                                    {
                                        Toast.makeText(getActivity(),"Record updated failed...",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Wrong credentials",Toast.LENGTH_SHORT).show();
                            }
                        }catch(JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");

                    }
                })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization",Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_TOKEN));
                return headers;

            }
        };
        requestQueue.add(jor);
    }

    private void getViewdata()

    {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,  "https://inceptivetechnologies.com/intranet/leave_management/api/leaveapi.php/getleaveslist",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        String   text=response;
                        System.out.println(response);
                        Log.e("ViewLeave " , "GET Data "+response );
                        try {
                            JSONObject jObj = new JSONObject(response.toString());
//                            String token= Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_TOKEN);

                            if (jObj.getString("status").equals("200")) {
                                if (jObj.getString("status_message").equals("Unauthorized User")) {

                                    Toast.makeText(getActivity(),"Session time out ! Please try again",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(ctx, EmployeeLogin.class);
                                    startActivity(intent);
                                } else {
                                    if (jObj.getString("status_message").equals("Success")) {


                                        JSONArray array = jObj.getJSONArray("data");

                                        for (int i = 0; i < array.length(); i++) {
                                            JSONObject jsonObject = array.getJSONObject(i);
                                            viewList item = new viewList();

                                            item.remaining_count=jsonObject.getInt("remaining_count");
                                            item.user_id=jsonObject.getString("user_id");
                                            item.leave_count=jsonObject.getString("leave_count");
                                            item.first_name=jsonObject.getString("first_name");

                                            item.last_name=jsonObject.getString("last_name");
                                            item.manager_leaveid = jsonObject.getString("leave_id");
                                            String leave_id = jsonObject.getString("leave_id");
                                            Utils.setStringPreferences(getActivity(), PrefTag.LV_ID,leave_id);
                                            FROM_DATE = jsonObject.getString("from_date");
                                            item.from_date = FROM_DATE;
                                            TO_DATE = jsonObject.getString("to_date");
                                            item.to_date = TO_DATE;
                                            item.reason = jsonObject.getString("reason");
                                            item.leave_status = jsonObject.getString("leave_status");
                                            String leave_status = jsonObject.getString("leave_status");
                                            Utils.setStringPreferences(getActivity(), PrefTag.LEAVE_STATUS, leave_status);



                                            viewlist.add(item);
                                        }
//                                        viewlist.clear();
                                        mAdapter.notifyDataSetChanged();


                                    }
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

                        Toast.makeText(getActivity(),"volley error",Toast.LENGTH_LONG).show();

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

    private void setRefreshing(boolean b) {
    }


//
//    @Override
//    public void onClick(View v) {
//
////        spinnerdrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//    }

    @Override
    public void updateR(String leave_id, String s_leave_id) {
        updateRecord(leave_id,s_leave_id);
    }

    @Override
    public void getData()
    {
        getViewdata();
        ManagerHome viewLeave = new ManagerHome();

        android.support.v4.app.FragmentManager fragmentManager;
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content_frame, viewLeave, FragTags.VWLV).addToBackStack(FragTags.VWLV).commit();

    }


    @Override
    public void managee() {

    }
}

