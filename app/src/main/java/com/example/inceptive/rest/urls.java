package com.example.inceptive.rest;

/**
 * Created by inceptive on 24/7/18.
 */

public class urls {
    public static String BASE_URL = "https://inceptivetechnologies.com/intranet/";
    public static String LOGIN_URL = BASE_URL + "leave_management/api/userapi.php/login";
    public static String EMPLOYEE_STATUS_URL = BASE_URL + "leave_management/api/leaveapi.php/getleaves?";

    public static String DASHBOARD_URL = BASE_URL + "leave_management/api/dashboardapi.php/dashboard?";
    public static String TYPE_URL = BASE_URL + "leave_management/api/leaveapi.php/leavetypes";

    public static String KEY_STATUS = "success";

    public static final String SHARED_PREF_NAME = "myloginapp";

    public static final String EMAIL_SHARED_PREF = "email";

    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static final String LOGOUT_API =BASE_URL+ "leave_management/api/userapi.php/logout?";
    public static final String UPDATE_URL=BASE_URL+"leave_management/api/leaveapi.php/changeleavestatus";
    public static final String EMPLOYEE_LIST = BASE_URL + "leave_management/api/dashboardapi.php/employeelisting";

    public static final String APPLY_LEAVE = BASE_URL + "leave_management/api/leaveapi.php/applyleave";
    public static final String STATUS_UPDATE = BASE_URL + "leave_management/api/leaveapi.php/leavetypes";
    public static final String MANAGER_STATUS = BASE_URL + "leave_management/api/leaveapi.php/getleaveslist";
}