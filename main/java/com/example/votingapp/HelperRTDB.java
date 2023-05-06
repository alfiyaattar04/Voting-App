package com.example.votingapp;

public class HelperRTDB {


    String name,email,password,mobNo,activity,uid;


    public HelperRTDB(String name, String email, String password, String mobNo, String activity, String uid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobNo = mobNo;
        this.activity = activity;
        this.uid = uid;
    }
    public HelperRTDB() {

    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
