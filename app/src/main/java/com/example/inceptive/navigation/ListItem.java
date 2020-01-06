package com.example.inceptive.navigation;

/**
 * Created by inceptive on 30/7/18.
 */

public class ListItem  {


    public String first_name;
    public String last_name;
    public String email;
    public  String employee_designation;
    public  String emp_id;
    public String date;
    String text1;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmployee_designation() {
        return employee_designation;
    }

    public void setEmployee_designation(String employee_designation) {
        this.employee_designation = employee_designation;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public String getEmail()
    {
        return email;
    }


    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getText1() {

        return text1;

    }
}
