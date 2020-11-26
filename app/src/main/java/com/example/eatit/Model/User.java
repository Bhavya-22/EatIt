package com.example.eatit.Model;

public class User {

    String Name;
    String Password;
    //Now we need phone for shipper to get the address
    String Phone;
    //As we modify server with IsStaff data so we need to modify user class with defaut value=false
    String IsStaff;

    public User() {

    }

    public User(String name, String password) {
        Name = name;
        Password = password;
        IsStaff="false";

    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
