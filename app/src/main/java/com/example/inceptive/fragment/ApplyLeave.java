
package com.example.inceptive.fragment;


import android.app.DatePickerDialog;
//import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.example.inceptive.navigation.PrefTag;
import com.example.inceptive.navigation.R;
import com.example.inceptive.rest.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class ApplyLeave extends Fragment implements DatePicker.OnDateChangedListener, AdapterView.OnItemSelectedListener, View.OnFocusChangeListener {

    Context ctx;
    DatePickerDialog datePickerDialog ;
    TextView apply_leave,from_date,to_date,leave_type,reason,edtfrom_Date,edtto_Date;
    View v;
    RelativeLayout applyLeave_relativeLayout;
    LinearLayout applyLeave_linearLayout;
    EditText  reason_desc, counttextview;
    Spinner leaveType_spinner;
    Button calcbutton;
    TextView countetxt,countno;
    String leave_Id;
    String finalDate;
    String err;
    int nothingSelectedFlag=0;
//    TextInputLayout fromtextinput;
//    FormDate date_picker;
//    ToDate date_picker2;
String leave_name;
    Button apply_btn;
    String[] lvtype = new String[]{
            "- - Select Leave Type - -" };

    private Object year;

    String URL_LEAVE = "https://inceptivetechnologies.com/intranet/leave_management/api/leaveapi.php/leavetypes";

    ArrayList<Date>dates = new ArrayList<>();
    int flag = 0;


    FragmentManager fragmentManager;

    ArrayList<String> CountryName;
    private Object month;
    private Object day;
    private int flag1=0;
    private int flag2=1;
    private String flag3="1";
    private String flag4="0";


    public ApplyLeave() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (v == null) {
            v = inflater.inflate(R.layout.applylv, container, false);
            initialize();

        }
        return v;
    }

   
    private void initialize() {


        ctx = getActivity() ;
//        applyLeave_relativeLayout=(RelativeLayout)v.findViewById(R.id.applyLeave_relativeLayout);
        from_date = (TextView)v.findViewById(R.id.from_date);
        to_date = (TextView)v.findViewById(R.id.to_date);

        counttextview=(EditText) v.findViewById(R.id.counttextview);
        leave_type = (TextView)v.findViewById(R.id.leave_type);
        reason = (TextView)v.findViewById(R.id.reason);
        apply_btn=(Button) v.findViewById(R.id.apply_btn);
        reason_desc=(EditText)v.findViewById(R.id.reason_desc);
        edtfrom_Date=(TextView)v.findViewById(R.id.edtfrom_Date);
        edtfrom_Date.setKeyListener(null);
//        apply_btn=(Button)v.findViewById(R.id.emploginbtn1);
        edtto_Date=(TextView)v.findViewById(R.id.edtto_Date);
         leaveType_spinner=(Spinner)v.findViewById(R.id.leaveType_spinner);
        edtfrom_Date.setInputType(InputType.TYPE_NULL);
        edtto_Date.setInputType(InputType.TYPE_NULL);
//        date_picker = new FormDate(getActivity(),edtfrom_Date);
//        date_picker2 = new ToDate(getActivity(),edtto_Date);
//        date_picker.setMinDate(System.currentTimeMillis());
//        date_picker2.setMinDate(System.currentTimeMillis());
//
//        fromtextinput=(TextInputLayout)v.findViewById(R.id.fromtextinput);

        CountryName=new ArrayList<>(Arrays.asList(lvtype));


        counttextview=(EditText) v.findViewById(R.id.counttextview);
        edtfrom_Date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(ctx,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Log.e("From Date ", dayOfMonth + "-"
                                        + (monthOfYear+1) + "-" + year  ) ;
                                String dd = "" + dayOfMonth;
                                String mm = "" + (monthOfYear + 1);
                                String yy = "" + year;
                                if (mm.trim().length() == 1) {
                                    mm = "0" + mm;
                                }
                                if (dd.trim().length() == 1) {
                                    dd = "0" + dd;
                                }
                                String ff = "";

                                edtfrom_Date.setText(dd + "-"
                                        + mm + "-" + yy );



                                 finalDate = leaveCount();
                                if(edtfrom_Date!=null &&  edtto_Date!=null)
                                {
                                    counttextview.setText( finalDate );
                                }
//                                counttextview.setText( finalDate );
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

                                counttextview.setText("");

            }
        });

        edtto_Date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                final String toda = edtfrom_Date.getText().toString();
                final String tofrmda=edtto_Date.getText().toString();

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(ctx,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Log.e("ToDate ", dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year  ) ;

//                                edtto_Date.setText(dayOfMonth + "-"
//                                        + (monthOfYear + 1) + "-" + year );
                                String dd = "" + dayOfMonth;
                                String mm = "" + (monthOfYear + 1);
                                String yy = "" + year;
                                if (mm.trim().length() == 1) {
                                    mm = "0" + mm;
                                }
                                if (dd.trim().length() == 1) {
                                    dd = "0" + dd;
                                }
                                String ff = "";

                                edtto_Date.setText(dd + "-"
                                        + mm + "-" + yy );

     finalDate = leaveCount();
    counttextview.setText( finalDate );

                         }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                String dt=edtfrom_Date.getText().toString();

                counttextview.setText("");


            }

        });



        loadSpinnerData(URL_LEAVE);

        leaveType_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String leave_name = leaveType_spinner.getSelectedItem().toString();
                if(leave_name.equals("- - Select leave type - -"))
                {
                    leave_Id = "0";
                    if(position==0)
                    {
                        nothingSelectedFlag=0;
                    }
                    else
                    {
                        nothingSelectedFlag=1;
                    }

                }
              else  if(leave_name.equals("Personal Leave"))
                {
                    Toast.makeText(getActivity(),"Personal leave type selected ",Toast.LENGTH_SHORT).show();

                    leave_Id = "1";
                    nothingSelectedFlag=1;
                }
                else if (leave_name.equals("Sick Leave"))
                {
                    Toast.makeText(getActivity(),"Sick leave type  selected ",Toast.LENGTH_SHORT).show();

                    leave_Id = "2";
                    nothingSelectedFlag=1;
                }
                else if(leave_name.equals("Casual Leave"))
                {
                    Toast.makeText(getActivity(),"Casual leave type  selected ",Toast.LENGTH_SHORT).show();

                    leave_Id = "3";
                    nothingSelectedFlag=1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                String leave_name = leaveType_spinner.getSelectedItem().toString();

                    leave_Id = "1";
                    nothingSelectedFlag=1;


            }
        });
        counttextview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveCount();


            }
        });

//
        apply_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtfrom_Date.getText().toString().length()==0 && edtto_Date.getText().toString().length()==0 && reason_desc.getText().toString().length()==0)

                        {
                            Toast.makeText(getActivity(),"Required all fields",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            switch (v.getId()) {

                                case R.id.apply_btn:
                                    boolean invalid = false;

                                    if (edtfrom_Date.getText().toString().length() == 0) {


                                        edtfrom_Date.setError("Please enter leave from date");
                                        return;

                                    } else if (edtto_Date.getText().toString().length() == 0) {

                                        edtto_Date.setError("Please enter leave to date");
                                        return;
                                    }
                                    else if(finalDate.equals("0")){

                                        Toast.makeText(ctx,"From date must before to date",Toast.LENGTH_SHORT).show();
                                       counttextview.setError("Select Leave Dates");
                                        return;

                                    }
                                    else if(nothingSelectedFlag==0){
                                        Toast.makeText(ctx,"Please select leave type",Toast.LENGTH_SHORT).show();
                                        setSpinnerError(leaveType_spinner,err);
                                        return;
                                    }
                                    else if (reason_desc.getText().toString().length() == 0) {

                                        reason_desc.setError("Please enter your leave reason");
                                        return;
                                    } else {
                                        shoeAlert(v);
                                    }
                                    break;
                            }
                        }
            }
        });
    }
    static public void setSpinnerError(Spinner leaveType_spinner, String error){
        View selectedView = leaveType_spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError(error);
        }
    }
    private void shoeAlert(View v) {

        AlertDialog.Builder myAlert=new AlertDialog.Builder(ctx, R.style.AlertDialogTheme);
        myAlert.setMessage("Do you want to apply leave?")
                .setTitle("Apply Leave")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        log_in(leave_Id);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(getActivity(),"Application cancelled",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create();
        myAlert.show();

    }


    @Override
    public void onResume() {
        super.onResume();



        if (getActivity() instanceof HeaderApp) {
            ((HeaderApp) getActivity()).HeaderName("Apply Leave ");
        }

    }

    public String leaveCount()
    {

        String fromDate = edtfrom_Date.getText().toString();
        String ToDate = edtto_Date.getText().toString();

        if(!fromDate.equals("") && !ToDate.equals("")) {
            Date dt1 = new Date();
            Date dt2 = new Date();

            SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyy");
            try {
                try {
                    dt1 = myFormat.parse(fromDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dt2 = myFormat.parse(ToDate);

                if(dt1.before(dt2))
                {
                    String date = checkDateArray(dt1, dt2);
                    return  date;

                }
                else if(dt1.equals(dt2))
                    {
                        String date = checkDateArray(dt1, dt2);

                        return date;
                   }

                else
                {
                    return flag4;
//                    counttextview.setText("");
//                    Toast.makeText(getActivity(),"From Date Must before To Date",Toast.LENGTH_SHORT).show();
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return fromDate;
        }
        else
        {
            String str="0";
            return str;

        }
    }


    private String checkDateArray(Date dt1, Date dt2)
    {
        Calendar cal1;
        cal1 = Calendar.getInstance();
        cal1.setTime(dt1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(dt2);

        getdate(cal1,cal2);
flag=0;

        for(int i = 0; i < dates.size();i++)
        {
            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat("EEEE");

            Date d = new Date();
            d = dates.get(i);
            String dayofthedate;
            dayofthedate = sdf.format(d);
            if(dayofthedate.equals("Sunday") || dayofthedate.equals("Saturday") )
            {

            }
            else
            {
                flag++;
            }
        }
        dates.clear();

        return String.valueOf(flag) ;
    }


    private void loadSpinnerData(String url) {


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)

                    {

                        String   text=response;
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals("200")) {
                                if (jsonObject.getString("status_message").equals("Unauthorized User")) {

                                    Toast.makeText(getActivity(),"Session time out ! Please try again",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(ctx, EmployeeLogin.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    if (jsonObject.getString("status_message").equals("Success")) {
                                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                                        for (int i = 0; i < jsonArray.length(); i++)
                                        {
                                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                            String leave_id = jsonObject1.getString("leave_type_id");
                                            String leave_type = jsonObject1.getString("leave_type");
                                            CountryName.add(leave_type);
                                            leaveType_spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, CountryName));

                                        }
                                    }
                                }

                            }
                        } catch (JSONException e)
                        {
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



    public void log_in(String leave_type_id)
    {

        Log.e("ApplyLeave ","inside log_in ") ;
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        Map<String, String> params = new HashMap<String, String>();
                params.put("employee_id",  Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_ID));
                params.put("from_date", edtfrom_Date.getText().toString());
                params.put("leave_count", counttextview.getText().toString());
                params.put("leave_type_id",leave_type_id);
                params.put("to_date",edtto_Date.getText().toString());
                  params.put("reason",reason_desc.getText().toString());

            Set<Map.Entry<String, String>> entries = params.entrySet();
           for ( Map.Entry key : entries ) {
            Log.e("key : "+key.getKey()   , "Value : "+key.getValue() );
        }
            Log.e("before ", "jsonobject");
        final JSONObject jsonObj = new JSONObject(params);
        Log.e("after ", "jsonobject");

//        String url = "https://inceptivetechnologies.com/intranet/leave_management/api/leaveapi.php/applyleave";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST,"https://inceptivetechnologies.com/intranet/leave_management/api/leaveapi.php/applyleave", jsonObj,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
               try{
                   Log.e("ApplyLeave ","inside log_in "+ response) ;

                   JSONObject jObj = new JSONObject(response.toString());
                   if (jObj.getString("status").equals("200"))
                   {
                       if(jObj.getString("status_message").equals("Unauthorized User"))
                       {

                           Toast.makeText(getActivity(),"Session time out ! Please try again",Toast.LENGTH_SHORT).show();

                           Intent intent=new Intent(ctx, EmployeeLogin.class);
                           startActivity(intent);
                       }
                       else if(jObj.getString("status_message").equals("Success"))
                       {
                           Toast.makeText(getActivity(), "Leave application submited!!", Toast.LENGTH_SHORT).show();
                          flag1++;


                           JSONArray ja = jObj.getJSONArray("data");
                           for(int i=0; i < ja.length(); i++)
                           {
                               JSONObject jsonObject = ja.getJSONObject(i);
                               String leave_id = jsonObject.getString("leave_id");
                           }

                           LeaveStatus leaveStatus = new LeaveStatus();

                           android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                           fragmentManager.beginTransaction().replace(R.id.emphome_content_frame, leaveStatus, FragTags.LEAVE_STATUS).addToBackStack(FragTags.LEAVE_STATUS).commit();

                       }
                       else
                       {
                       }
                   }
                   else
                   {
                   }
               }catch(JSONException e)
               {
                   e.printStackTrace();
               }
           }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error : "+ error);
                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                Log.e("Authorization token : " , Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_TOKEN) );
                headers.put("Authorization",Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_TOKEN));
                Log.v("Token", "token=" + Utils.getStringPreferences(getActivity(), PrefTag.EMPLOYEE_TOKEN));
                return headers;
            }
        };
        requestQueue.add(jor);
    }



        public void onStart()
    {
        super.onStart();

    }


    private List<Date> getdate(Calendar cal1, Calendar cal2)
    {

            while(!cal1.after(cal2))
            {
                dates.add(cal1.getTime());
                cal1.add(Calendar.DATE, 1);
            }

        return dates;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void callParentMethod(){
        getActivity().onBackPressed();
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {


    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        leaveCount();
    }
}
