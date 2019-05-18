package com.univ.webService.dataModel;

public class PeopleInRegion {
    private int idPeople;
    private int idArea;
    private int numberOfPeople;
    private int idWeek;

    public PeopleInRegion(int idPeople, int idArea, int numberOfPeople, int idWeek) {
        this.idPeople = idPeople;
        this.idArea = idArea;
        this.numberOfPeople = numberOfPeople;
        this.idWeek = idWeek;
    }

    public int getIdWeek() {
        return idWeek;
    }

    public int getIdPeople() {
        return idPeople;
    }

    public int getIdArea() {
        return idArea;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setIdPeople(int idPeople) {
        this.idPeople = idPeople;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setIdWeek(int idWeek) {
        this.idWeek = idWeek;
    }
}
