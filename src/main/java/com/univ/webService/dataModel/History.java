package com.univ.webService.dataModel;

public class History {
    private int idHistory;
    private int idAbonent;
    private int idWeek;
    private String tariffName;
    private String status;
    private String date;

    public History(int idHistory, int idAbonent, int idWeek, String tariffName, String status, String date) {
        this.idHistory = idHistory;
        this.idAbonent = idAbonent;
        this.idWeek = idWeek;
        this.tariffName = tariffName;
        this.status = status;
        this.date = date;
    }

    public int getIdWeek() {
        return idWeek;
    }
    public int getIdHistory() {
        return idHistory;
    }

    public int getIdAbonent() {
        return idAbonent;
    }

    public String getTariffName() {
        return tariffName;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public void setIdHistory(int idHistory) {
        this.idHistory = idHistory;
    }

    public void setIdAbonent(int idAbonent) {
        this.idAbonent = idAbonent;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdWeek(int idWeek) {
        this.idWeek = idWeek;
    }
}
