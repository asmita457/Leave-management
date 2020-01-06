package com.example.inceptive.navigation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.inceptive.rest.Utils;
import com.example.inceptive.rest.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeLogin extends AppCompatActivity implements View.OnClickListener{


    EditText edtPassword, edtEmail;
    String emailId,pass;

    Button emploginbtn1;




    private boolean loggedIn = false;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);


        edtPassword = (EditText) findViewById(R.id.edtPassword);

        edtEmail = (EditText) findViewById(R.id.edtEmail);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();

        }

        emploginbtn1 = (Button) findViewById(R.id.emploginbtn1);
        emploginbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                        "\\@" +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                        "(" +

                        "\\." +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                        ")+";


                String mailID = edtEmail.getText().toString();

                Matcher matcher = Pattern.compile(validemail).matcher(mailID);

                if (!matcher.matches())
                {
                    Toast.makeText(getApplicationContext(),"Oops!Fields should not be enpty.",Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(),
//                            "", Toast.LENGTH_SHORT).show();
//                    edtEmail.setError("This email does not exist in our record");
                    return;
                }
                else if (edtPassword.getText().toString().equals("") ) {

                    Toast.makeText(getApplicationContext(),"Oops!Fields should not be enpty.",Toast.LENGTH_SHORT).show();

//                    edtPassword.setError("Please Enter valid password");
                    return;
                }
                else
                {
                    emailId = edtEmail.getText().toString();
                    pass = edtPassword.getText().toString();

                    log_in(emailId,pass);

                }


            }
        });


    }




    public void log_in(final String emailid,String passw)
    {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", emailid);
        params.put("password", passw);

        final JSONObject jsonObj = new JSONObject(params);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, urls.LOGIN_URL, jsonObj,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {

                        Log.e("response",""+response);
                        try{
                            JSONObject jObj = new JSONObject(response.toString());
                            if (jObj.getString("status").equals("200"))
                            {
                                if(jObj.getString("status_message").equals("Your account has been deactivated"))
                                {
                                    Toast.makeText(getApplicationContext(),"Your account has been deactivated",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(EmployeeLogin.this,EmployeeLogin.class);
                                    startActivity(intent);
                                }
                                else if(jObj.getString("status_message").equals("Login Successful"))
                                {
                                    JSONArray ja = response.getJSONArray("data");
                                    for(int i=0; i < ja.length(); i++)
                                    {
                                        JSONObject jsonObject = ja.getJSONObject(i);
                                        String user_role =  jsonObject.getString("user_role");
                                        String user_id = jsonObject.getString("user_id");
                                        String auth_token = jsonObject.getString("auth_token");
                                        String first_name=jsonObject.getString("first_name");
                                        String last_name=jsonObject.getString("last_name");
                                        String email=jsonObject.getString("email");
                                        Log.e("auth_token", auth_token);

                                        Utils.setStringPreferences(EmployeeLogin.this, PrefTag.EMPLOYEE_ID,user_id);
                                        Utils.setStringPreferences(EmployeeLogin.this, PrefTag.EMPLOYEE_TOKEN,auth_token);
                                        Utils.setStringPreferences(EmployeeLogin.this, PrefTag.USER_ROLE,user_role);

                                        Utils.setStringPreferences(EmployeeLogin.this,PrefTag.EMAIL_ID,email);
                                        if(user_role.equals("Employee"))
                                        {

                                            Utils.setStringPreferences(EmployeeLogin.this, PrefTag.FIRST_NAME,first_name);
                                            Utils.setStringPreferences(EmployeeLogin.this, PrefTag.LAST_NAME,last_name);

                                            Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();

                                            Intent intent=new Intent(EmployeeLogin.this,EmployeeHome.class);
                                            startActivity(intent);

                                        }
                                        else
                                        {

                                            Utils.setStringPreferences(EmployeeLogin.this, PrefTag.FIRST_NAME,first_name);
                                            Utils.setStringPreferences(EmployeeLogin.this, PrefTag.LAST_NAME,last_name);

                                            Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();

                                            Intent intent=new Intent(EmployeeLogin.this,MainActivity.class);
                                            startActivity(intent);


                                        }
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext()," Username/password invalid. Please try again.",Toast.LENGTH_LONG).show();
                                }

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Wrong credentials",Toast.LENGTH_LONG).show();
                            }



                        }catch(JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error"+error);

                    }
                }
        );
        requestQueue.add(jor);

    }








    @Override
    protected void onResume() {
        super.onResume();
        if(loggedIn){
            edtPassword.setText("");
            edtEmail.setText("");
            //We will start the Profile Activity
            Intent intent = new Intent(EmployeeLogin.this, MainActivity.class);
            startActivity(intent);
        }
    }



    @Override
    public void onClick(View v) {

    }


//    @Override
//    public void onBackPressed()
//    {
//    }
}