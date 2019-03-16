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

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getContractID() {
        return contractID;
    }

    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }

    public Integer getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(Integer agencyID) {
        this.agencyID = agencyID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
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
}
