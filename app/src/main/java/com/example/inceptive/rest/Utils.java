package com.example.inceptive.rest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by inceptive on 30/7/18.
 */

public class Utils
{
    public static final String PrefName = "Inceptive_Leave_App";


    public static void setStringPreferences(Context con, String key, String value)
    {
        SharedPreferences preferences = con.getSharedPreferences(PrefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringPreferences(Context con, String key)
    {
        SharedPreferences sharedPreferences = con.getSharedPreferences(PrefName, 0);
        return sharedPreferences.getString(key, "");
    }

    public static void getStringPreferences(Activity activity, String leaveId, String leave_status_id) {
        SharedPreferences preferences = activity.getSharedPreferences(PrefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(leaveId, leave_status_id);
        editor.commit();
    }

    public static String setStringPreferences(Activity activity, String remainingCount) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PrefName, 0);
        return sharedPreferences.getString(remainingCount, "");

    }
}
