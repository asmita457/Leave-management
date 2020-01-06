package com.example.inceptive.navigation;

/**
 * Created by inceptive on 1/8/18.
 */

public class managerpendinglist {



    public String sts;
    public int employee_id;
    public  String from_date;
    public String to_date;
    public  String reason;
    public String leave_status;
    public  String first_name;
    public  String last_name;

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getTo_date() {
        return to_date;
    }

    public String getFrom_date() {
        return from_date;
    }


    public String getLeave_status() {
        return leave_status;
    }

    public String getReason() {
        return reason;
    }


    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public void setLeave_status(String leave_status) {
        this.leave_status = leave_status;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }


    public void setReason(String reason) {
        this.reason = reason;
    }


}
