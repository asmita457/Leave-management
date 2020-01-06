package com.example.inceptive.navigation;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerLogin extends AppCompatActivity  {


    EditText manager_edtPassword,manager_edtEmail;
    LinearLayout managerlogin_linearLayout,managerlogin_linearLayout2,managerlogin_linearLayout3;
    Button manager_loginbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);


        manager_edtPassword=(EditText)findViewById(R.id.manager_edtPassword);
        manager_edtEmail=(EditText)findViewById(R.id.manager_edtEmail);
        manager_loginbtn=(Button)findViewById(R.id.manager_loginbtn);
        managerlogin_linearLayout=(LinearLayout)findViewById(R.id.managerlogin_linearLayout);
        managerlogin_linearLayout2=(LinearLayout)findViewById(R.id.managerlogin_linearLayout2);
        managerlogin_linearLayout3=(LinearLayout)findViewById(R.id.managerlogin_linearLayout3);
        ImageView manager_imageView=(ImageView)findViewById(R.id.manager_imageView);
        ScrollView manager_scrollview=(ScrollView)findViewById(R.id.manager_scrollview);

        manager_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                        "\\@" +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                        "(" +

                        "\\." +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                        ")+";


                String email = manager_edtEmail.getText().toString();

                Matcher matcher= Pattern.compile(validemail).matcher(email);

                if (!matcher.matches())
                {
                    Toast.makeText(getApplicationContext(),"enter valid email",Toast.LENGTH_LONG).show();

                }
                else if(manager_edtPassword.getText().toString().equals("") || manager_edtPassword.getText().toString().length() < 8)
                {
                    Toast.makeText(getApplicationContext(), "write 8 digit password", Toast.LENGTH_LONG).show();
                }

                else
                {
                    Intent intent=new Intent(ManagerLogin.this,MainActivity.class);
                    startActivity(intent);
                }


            }
        });


    }







}
