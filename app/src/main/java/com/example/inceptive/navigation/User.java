package com.example.inceptive.navigation;

/**
 * Created by inceptive on 25/7/18.
 */

public class User {

    private int id;
    private String email;
    private String password,leave_type_id,leave_type;

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;

    }


    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getLeave_type_id()
    {
        return leave_type_id;
    }

    public void setLeave_type_id(String leave_type_id)
    {
        this.leave_type_id = leave_type_id;
    }

    public String getLeave_type()
    {
        return leave_type;
    }

    public void setLeave_type(String leave_type
    ) {
        this.leave_type = leave_type;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
