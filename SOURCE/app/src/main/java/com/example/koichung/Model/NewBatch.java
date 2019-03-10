package com.example.koichung.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewBatch {
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
    @SerializedName("buyer")
    @Expose
    public String buyer;
    @SerializedName("note")
    @Expose
    public String note;
    @SerializedName("privateNote")
    @Expose
    public String privateNote;
    @SerializedName("lastCount")
    @Expose
    public Integer lastCount;
    @SerializedName("isShowWeb")
    @Expose
    public Integer isShowWeb;
    @SerializedName("isHaveContract")
    @Expose
    public Integer isHaveContract;
    @SerializedName("createDate")
    @Expose
    public String createDate;
    @SerializedName("listagency")
    @Expose
    public List<ListAgency> listagency = null;

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

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrivateNote() {
        return privateNote;
    }

    public void setPrivateNote(String privateNote) {
        this.privateNote = privateNote;
    }

    public Integer getLastCount() {
        return lastCount;
    }

    public void setLastCount(Integer lastCount) {
        this.lastCount = lastCount;
    }

    public Integer getIsShowWeb() {
        return isShowWeb;
    }

    public void setIsShowWeb(Integer isShowWeb) {
        this.isShowWeb = isShowWeb;
    }

    public Integer getIsHaveContract() {
        return isHaveContract;
    }

    public void setIsHaveContract(Integer isHaveContract) {
        this.isHaveContract = isHaveContract;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<ListAgency> getListagency() {
        return listagency;
    }

    public void setListagency(List<ListAgency> listagency) {
        this.listagency = listagency;
    }
}
