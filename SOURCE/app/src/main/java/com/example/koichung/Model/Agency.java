package com.example.koichung.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Agency implements Serializable {
    @SerializedName("$id")
    @Expose
    public String $id;
    @SerializedName("agencyID")
    @Expose
    public Integer agencyID;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("userName")
    @Expose
    public String userName;
    @SerializedName("role")
    @Expose
    public Integer role;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("phonenumber")
    @Expose
    public String phonenumber;
    public boolean check=false;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Agency() {
    }

    public Agency(String $id, Integer agencyID, String name, String userName, Integer role, String address, String phonenumber) {
        this.$id = $id;
        this.agencyID = agencyID;
        this.name = name;
        this.userName = userName;
        this.role = role;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Integer getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(Integer agencyID) {
        this.agencyID = agencyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
