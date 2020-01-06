package com.example.inceptive.navigation;

/**
 * Created by inceptive on 18/9/18.
 */

public class Model {

    public String first_name;
    public String last_name;
    public String emp_id;

    public String getLast_name() {
        return last_name;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
