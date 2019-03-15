package com.example.koichung.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("$id")
    @Expose
    public String $id;
    @SerializedName("orderID")
    @Expose
    public Integer orderID;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("contractID")
    @Expose
    public Integer contractID;
    @SerializedName("agencyID")
    @Expose
    public Integer agencyID;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("totalPrice")
    @Expose
    public Integer totalPrice;
    @SerializedName("qty")
    @Expose
    public Integer qty;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("createDateString")
    @Expose
    public String createDateString;
    @SerializedName("createDate")
    @Expose
    public String createDate;
    @SerializedName("isActive")
    @Expose
    public Integer isActive;
}
