package com.example.inceptive.navigation;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.inceptive.fragment.ApplyLeave;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by inceptive on 30/7/18.
 */
public class ToDate implements LeavecountInter
{
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    final SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy");
    DatePickerDialog dp;
    Context ctx;
    EditText edttt;
    int y = 0, m = 0, d = 0;
    ApplyLeave applyLeave;


    public ToDate(Context ctx, EditText edttt)
    {
        this.ctx = ctx;
        this.edttt = edttt;
        Open_Dialog();
    }


    private void Open_Dialog()
    {
        if (edttt.getText().toString().trim().length() != 0)
        {
            try {
                Date d1 = sdf.parse(edttt.getText().toString().trim());
                Calendar c = Calendar.getInstance();
                c.setTime(d1);
                y = c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DAY_OF_MONTH);

            } catch (Exception ee) {
                ee.printStackTrace();
            }
        } else
        {
            Calendar c = Calendar.getInstance();
            y = c.get(Calendar.YEAR);
            m = c.get(Calendar.MONTH);
            d = c.get(Calendar.DAY_OF_MONTH);
        }
        dp = new DatePickerDialog(ctx,
                new DatePickerDialog.OnDateSetListener()
                {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

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

                        try {
                            Date date = formatter.parse((yy + "-" + mm + "-" + dd));
                            ff = sdf.format(date);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        edttt.setText(ff);

                    }

                }, y, m, d);


    }

    public void setTitle(String title) {
        dp.setTitle(title);
    }

    public void setMessage(String msg) {
        dp.setMessage(msg);
    }

    public void setMaxDate(long tm)
    {
        dp.getDatePicker().setMaxDate(tm);
    }

    public void setMinDate(long tm) {
        dp.getDatePicker().setMinDate(tm);
    }

    public void show()
    {
        dp.show();
    }

    public String getFormetedDate()
    {
        try {
            Date d1 = sdf.parse(edttt.getText().toString().trim());
            return formatter.format(d1);


        } catch (ParseException e)
        {
            e.printStackTrace();
            return "";
        }

    }


    @Override
    public void leaveCount() {
        applyLeave.leaveCount();
    }
}