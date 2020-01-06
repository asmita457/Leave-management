package com.example.inceptive.navigation;

/**
 * Created by inceptive on 2/8/18.
 */

public class viewList {

//    ArrayList<viewList> viewlist;


    public String leave_id;
    public String user_id;
    public  String approved_by;
    public  String manager_leaveid;
    public  String from_date;
    public  String to_date;
    public  String reason;
    public int counttext;
    public  String leave_status;
    public int remaining_count;
    public String leave_count;

    public String first_name;

    public String last_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLeave_count() {
        return leave_count;
    }

    public int getRemaining_count() {
        return remaining_count;
    }

    public void setRemaining_count(int remaining_count) {
        this.remaining_count = remaining_count;
    }

    public void setLeave_count(String leave_count) {
        this.leave_count = leave_count;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getManager_leaveid() {
        return manager_leaveid;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public void setManager_leaveid(String manager_leaveid) {
        this.manager_leaveid = manager_leaveid;
    }

    public int getCounttext() {
        return counttext;
    }

    public void setCounttext(int counttext) {
        this.counttext = counttext;
    }

    public String getLeave_id()
    {
        return leave_id;
    }

    public String getTo_date() {
        return to_date;
    }

    public String getLeave_status() {
        return leave_status;
    }

    public String getReason() {
        return reason;
    }

    public String getFrom_date() {
        return from_date;
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

    public void setLeave_id(String leave_id) {
        this.leave_id = leave_id;
    }
}
