package com.example.koichung.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateBatchRespone {
    @SerializedName("$id")
    @Expose
    public String $id;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("result")
    @Expose
    public NewBatch result;
    @SerializedName("message")
    @Expose
    public String message;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public NewBatch getResult() {
        return result;
    }

    public void setResult(NewBatch result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
