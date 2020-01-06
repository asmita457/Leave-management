
package com.example.inceptive.fragment;


import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inceptive.navigation.EmployeeLogin;
import com.example.inceptive.navigation.HeaderApp;
import com.example.inceptive.navigation.PrefTag;
import com.example.inceptive.navigation.R;
import com.example.inceptive.navigation.ViewAdapter;
import com.example.inceptive.navigation.managerpendinglist;
import com.example.inceptive.rest.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewLeave extends Fragment implements View.OnClickListener
{

    String emailid;
    DrawerLayout fragment_manager_home;
    View v;
    RecyclerView recycler_view_manager;
   TextView manager_name,managerhome_leaveid,managerhome_empfrom,managerhome_empto,pending_leaves,managerhome_empreason,managerhome_empstatus;
   FragmentManager fragmentManager;
    RelativeLayout managerhome_relativeLayout;
    private ViewAdapter vAdapter;
    ArrayList<managerpendinglist> pendinglist = new ArrayList<>();
EditText managersearch;
ImageView crossss;
    public ViewLeave() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        if (v == null) {
            v = inflater.inflate(R.layout.manager_recyclerview, container, false);
            initialize();
        }
        return v;



    }

    private void initialize()
    {


        Log.d("User Name" ,"emailid");

        managerhome_relativeLayout=(RelativeLayout)v.findViewById(R.id.managerhome_relativeLayout);
        recycler_view_manager = (RecyclerView)v.findViewById(R.id.recycler_view_manager);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler_view_manager.setLayoutManager(llm);

        managersearch=(EditText)v.findViewById(R.id.managersearch);


        crossss=(ImageView)v.findViewById(R.id.crossss);
        crossss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managersearch.setText("");

            }
        });
        vAdapter = new ViewAdapter(getActivity(), pendinglist);
        recycler_view_manager.setAdapter(vAdapter);

        getdashboarddata();
//GetManagerName();
    managersearch.addTextChangedListener(new TextWatcher() {
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

    }

    private void filter(String s) {


        ArrayList<managerpendinglist> filteredList=new ArrayList<>();
        for(managerpendinglist item : pendinglist)
        {
            if(item.getFirst_name().toLowerCase().contains(s.toLowerCase()))
            {
                filteredList.add(item);
            }



            else if(item.getLast_name().toLowerCase().contains(s.toLowerCase()))
            {
                filteredList.add(item);
            }
        }

        vAdapter.FilterList(filteredList);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof HeaderApp) {
            ((HeaderApp) getActivity()).HeaderName("All Leave");

        }
    }


    private void getdashboarddata() {



        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        // Initialize a new JsonObjectRequest instance
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://inceptivetechnologies.com/intranet/leave_management/api/dashboardapi.php/dashboard?"+"employee_id"+"="+ Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_ID)+"&"+"role"+"="+Utils.getStringPreferences(getActivity(),PrefTag.USER_ROLE),
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.e("response",""+response);
                            JSONObject jObj = new JSONObject(response.toString());
                            if (jObj.getString("status").equals("200")) {
                                if (jObj.getString("status_message").equals("Unauthorized User"))
                                {
                                    Toast.makeText(getActivity(),"Session time out ! Please try again",Toast.LENGTH_SHORT).show();

//                                    Intent intent = new Intent(getActivity(), EmployeeLogin.class);
//                                    startActivity(intent);

                                }
                                else if (jObj.getString("status_message").equals("Success")) {


                                    JSONArray array = jObj.getJSONArray("data");

                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject jarray = array.getJSONObject(i);
                                        managerpendinglist item = new managerpendinglist();
                                        item.from_date = jarray.getString("from_date");
                                        item.to_date = jarray.getString("to_date");
                                        item.reason = jarray.getString("reason");
                                        item.leave_status = jarray.getString("leave_type");
                                        item.first_name = jarray.getString("first_name");
                                        item.last_name = jarray.getString("last_name");
                                        item.sts=jarray.getString("leave_status");
                                        pendinglist.add(item);
                                    }
                                    vAdapter.notifyDataSetChanged();

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


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {


        }

    }

    public void callParentMethod(){
        getActivity().onBackPressed();
    }
}











