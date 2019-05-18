package com.univ.webService.dataModel;

public class PeopleInTariff {
    private int idPeople;
    private int idTariff;
    private int numberOfPeople;
    private int idWeek;

    public PeopleInTariff(int idPeople, int idTariff, int numberOfPeople, int idWeek) {
        this.idPeople = idPeople;
        this.idTariff = idTariff;
        this.numberOfPeople = numberOfPeople;
        this.idWeek = idWeek;
    }

    public int getIdWeek() {
        return idWeek;
    }

    public int getIdPeople() {
        return idPeople;
    }

    public int getIdTariff() {
        return idTariff;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setIdPeople(int idPeople) {
        this.idPeople = idPeople;
    }

    public void setIdTariff(int idTariff) {
        this.idTariff = idTariff;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setIdWeek(int idWeek) {
        this.idWeek = idWeek;
    }
}
