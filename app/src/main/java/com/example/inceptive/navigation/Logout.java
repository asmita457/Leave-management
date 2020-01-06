package com.example.inceptive.navigation;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.inceptive.rest.Utils;
import com.example.inceptive.rest.urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by inceptive on 7/8/18.
 */

public  class Logout {
Context ctx;

    public Logout(EmployeeHome employeeHome)
    {
        this.ctx = employeeHome;
    }

    public Logout(MainActivity mainActivity) {
        this.ctx=mainActivity;
    }

    public void LogoutAPI()
    {

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(ctx);


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urls.LOGOUT_API+"userid"+"="+ Utils.getStringPreferences(ctx, PrefTag.EMPLOYEE_ID),
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response.toString());

                            if (jObj.getInt("status")== 200)
                            {
                            if (jObj.getString("status_message").equals("Logout Successful"))
                            {


//                                JSONArray array = jObj.getJSONArray("data");
//
//                                for (int i = 0; i < array.length(); i++)
//                                {
//
//                                    int leave_id = array.getInt(Integer.parseInt("user_id"));
//                                    if (PrefTag.EMPLOYEE_ID.equals(leave_id))
//                                    {
                                        Intent intent = new Intent(ctx, EmployeeLogin.class);
                                        ctx.startActivity(intent);
//                                    }
                                                                    }


//                            }
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ctx,"volley error",Toast.LENGTH_SHORT).show();

                    }


                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "c6eae16cbea2024ac5926bc3ec02d976");
                return headers;

            }



        };

        requestQueue.add(stringRequest);

    }




}
