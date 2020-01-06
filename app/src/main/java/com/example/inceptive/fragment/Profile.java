package com.example.inceptive.fragment;


import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.example.inceptive.navigation.HeaderApp;
import com.example.inceptive.navigation.ListItem;
import com.example.inceptive.navigation.PrefTag;
import com.example.inceptive.navigation.R;
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
public class Profile extends Fragment implements View.OnClickListener
{

    RelativeLayout profile_relativelayout;
    TextView profile_record;
    View v;
    FragmentManager fragmentManager;
    ImageView crosss;
    RecyclerView recycler_view_profile;
    FrameLayout  leaveprofile_frame;
    RelativeLayout leaveprofile_relativeLayout;
    LinearLayout leaveprofile_linearLayout;
    public ProfileAdapter pAdapter;
    ArrayList<ListItem> profilelist = new ArrayList<>();
    private  String email;
EditText searchedittext;




    public Profile() {
        // Required empty public constructor




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.leave_profile, container, false);
            initialize();
//
//            FragmentTransaction tx = fragmentManager.beginTransaction();
//            tx.replace( R.id.main_content_frame, new ManagerHome() ).addToBackStack( "tag" ).commit();


        }

      return v;

    }


    private void initialize()

    {

//        profile_relativelayout=(RelativeLayout)v.findViewById(R.id.profile_relativelayout);
        recycler_view_profile = (RecyclerView)v.findViewById(R.id.recycler_view_profile);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler_view_profile.setLayoutManager(llm);
        crosss=(ImageView)v.findViewById(R.id.crosss);
        crosss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchedittext.setText("");
            }
        });
        searchedittext=(EditText)v.findViewById(R.id.searchedittext);

        pAdapter = new ProfileAdapter(getActivity(), profilelist);
        recycler_view_profile.setAdapter(pAdapter);
//        recycler_view_profile.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//search=(SearchView)v.findViewById(R.id.search);
        getprofiledata();


        searchedittext.addTextChangedListener(new TextWatcher() {
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

        ArrayList<ListItem> filteredList=new ArrayList<>();
        for(ListItem item : profilelist)
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

        pAdapter.FilterList(filteredList);

    }




    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof HeaderApp) {
            ((HeaderApp) getActivity()).HeaderName("Profile");

        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void getprofiledata()
    {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://inceptivetechnologies.com/intranet/leave_management/api/dashboardapi.php/employeelisting",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jObj = new JSONObject(response.toString());
                            if (jObj.getString("status").equals("200")) {
                                if (jObj.getString("status_message").equals("Unauthorized User")) {
                                    Toast.makeText(getActivity(),"Session time out ! Please try again",Toast.LENGTH_SHORT).show();


//                                    Intent intent = new Intent(getActivity(), EmployeeLogin.class);
//                                    startActivity(intent);

                                } else if (jObj.getString("status_message").equals("Success")) {

                                    JSONArray array = jObj.getJSONArray("data");

                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject jsonObject = array.getJSONObject(i);
                                        ListItem item = new ListItem();

                                        item.first_name = jsonObject.getString("first_name");
                                        item.last_name = jsonObject.getString("last_name");
                                        item.email = jsonObject.getString("email");
                                        String email = jsonObject.getString("email");
                                        item.employee_designation = jsonObject.getString("employee_designation");
                                        item.emp_id=jsonObject.getString("emp_id");
                                        item.date=jsonObject.getString("date");
                                        profilelist.add(item);

                                    }
                                    pAdapter.notifyDataSetChanged();
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

    }

    public static class DetailedInfo extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detailed_info);
        }



    }


    public void callParentMethod(){
        getActivity().onBackPressed();
    }

}
