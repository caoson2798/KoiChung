package com.example.koichung.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportGeneral {
    @SerializedName("$id")
    @Expose
    public String $id;
    @SerializedName("totalContract")
    @Expose
    public Integer totalContract;
    @SerializedName("totalBatch")
    @Expose
    public Integer totalBatch;
    @SerializedName("totalAgency")
    @Expose
    public Integer totalAgency;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Integer getTotalContract() {
        return totalContract;
    }

    public void setTotalContract(Integer totalContract) {
        this.totalContract = totalContract;
    }

    public Integer getTotalBatch() {
        return totalBatch;
    }

    public void setTotalBatch(Integer totalBatch) {
        this.totalBatch = totalBatch;
    }

    public Integer getTotalAgency() {
        return totalAgency;
    }

    public void setTotalAgency(Integer totalAgency) {
        this.totalAgency = totalAgency;
    }
}
