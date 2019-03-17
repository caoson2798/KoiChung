package com.example.koichung.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportContract {
    @SerializedName("$id")
    @Expose
    public String $id;
    @SerializedName("numberOrders")
    @Expose
    public Integer numberOrders;
    @SerializedName("moneyBuyed")
    @Expose
    public Integer moneyBuyed;
    @SerializedName("totalFunds")
    @Expose
    public Integer totalFunds;
    @SerializedName("profit")
    @Expose
    public Integer profit;
    @SerializedName("dateFunds")
    @Expose
    public String dateFunds;
    @SerializedName("dateCommit")
    @Expose
    public String dateCommit;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Integer getNumberOrders() {
        return numberOrders;
    }

    public void setNumberOrders(Integer numberOrders) {
        this.numberOrders = numberOrders;
    }

    public Integer getMoneyBuyed() {
        return moneyBuyed;
    }

    public void setMoneyBuyed(Integer moneyBuyed) {
        this.moneyBuyed = moneyBuyed;
    }

    public Integer getTotalFunds() {
        return totalFunds;
    }

    public void setTotalFunds(Integer totalFunds) {
        this.totalFunds = totalFunds;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public String getDateFunds() {
        return dateFunds;
    }

    public void setDateFunds(String dateFunds) {
        this.dateFunds = dateFunds;
    }

    public String getDateCommit() {
        return dateCommit;
    }

    public void setDateCommit(String dateCommit) {
        this.dateCommit = dateCommit;
    }
}
