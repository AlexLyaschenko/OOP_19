package com.univ.webService;

public class Billing {
    private int idBilling;
    private int Balance;
    private int chargeAmount;
    private String connectionDate;
    private int idTariff;
    private String Status;

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setIdBilling(int idBilling) {
        this.idBilling = idBilling;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    public void setChargeAmount(int chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public void setConnectionDate(String connectionDate) {
        this.connectionDate = connectionDate;
    }

    public void setIdTariff(int idTariff) {
        this.idTariff = idTariff;
    }

    public int getIdBilling() {
        return idBilling;
    }

    public int getBalance() {
        return Balance;
    }

    public int getChargeAmount() {
        return chargeAmount;
    }

    public String getConnectionDate() {
        return connectionDate;
    }

    public int getIdTariff() {
        return idTariff;
    }
}
