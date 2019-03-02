package com.example.koichung.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("$id")
    @Expose
    public String $id;
    @SerializedName("userID")
    @Expose
    public Integer userID;
    @SerializedName("userName")
    @Expose
    public String userName;
    @SerializedName("role")
    @Expose
    public Integer role;
    @SerializedName("createDate")
    @Expose
    public String createDate;
    @SerializedName("isActive")
    @Expose
    public Integer isActive;
    @SerializedName("phone1")
    @Expose
    public String phone1;
    @SerializedName("phone2")
    @Expose
    public String phone2;
    @SerializedName("address")
    @Expose
    public String address;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
