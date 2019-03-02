package com.example.koichung.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batch {
    @SerializedName("$id")
    @Expose
    public String $id;
    @SerializedName("batchID")
    @Expose
    public Integer batchID;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("root")
    @Expose
    public String root;
    @SerializedName("dealer")
    @Expose
    public String dealer;
    @SerializedName("isShowWeb")
    @Expose
    public Integer isShowWeb;
    @SerializedName("buyer")
    @Expose
    public String buyer;
    @SerializedName("privateNote")
    @Expose
    public String privateNote;
    @SerializedName("note")
    @Expose
    public String note;
    @SerializedName("lastCount")
    @Expose
    public Integer lastCount;
    @SerializedName("createDate")
    @Expose
    public String createDate;
    @SerializedName("createDateV")
    @Expose
    public String createDateV;
    @SerializedName("isActive")
    @Expose
    public Integer isActive;
    @SerializedName("isHaveContract")
    @Expose
    public Integer isHaveContract;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Integer getBatchID() {
        return batchID;
    }

    public void setBatchID(Integer batchID) {
        this.batchID = batchID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public Integer getIsShowWeb() {
        return isShowWeb;
    }

    public void setIsShowWeb(Integer isShowWeb) {
        this.isShowWeb = isShowWeb;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getPrivateNote() {
        return privateNote;
    }

    public void setPrivateNote(String privateNote) {
        this.privateNote = privateNote;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getLastCount() {
        return lastCount;
    }

    public void setLastCount(Integer lastCount) {
        this.lastCount = lastCount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateV() {
        return createDateV;
    }

    public void setCreateDateV(String createDateV) {
        this.createDateV = createDateV;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsHaveContract() {
        return isHaveContract;
    }

    public void setIsHaveContract(Integer isHaveContract) {
        this.isHaveContract = isHaveContract;
    }
}
