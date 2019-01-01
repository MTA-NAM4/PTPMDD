package com.itproject.hoadt.b3liststudent;

import java.io.Serializable;

/**
 * Created by HoaDT on 11/9/2018.
 */
public class Student implements Serializable {
    private String Name;
    private String Address;
    private  String DateOfBirth;

    public Student(String name, String address, String dateOfBirth) {
        Name = name;
        Address = address;
        DateOfBirth = dateOfBirth;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
}
