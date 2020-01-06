package com.example.inceptive.navigation;

/**
 * Created by inceptive on 31/7/18.
 */

public class ModelStatus {

//    public String leave_type_id;
    public  int leave_id;
    public  String leave_type;
    public String from_date;
    public String to_date;
    public String status;
    public String reason;
    public  String approved_by;

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeave_type()
    {
        return leave_type;
    }


    public int getLeave_id() {
        return leave_id;
    }

    public void setLeave_type(String leave_type)
    {
        this.leave_type = leave_type;
    }

    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
    }

    public String getFrom_date()
    {
        return from_date;
    }

    public String getTo_date()
    {
        return to_date;
    }


    public void setFrom_date(String from_date)
    {
        this.from_date = from_date;
    }


    public void setTo_date(String to_date)
    {
        this.to_date = to_date;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
