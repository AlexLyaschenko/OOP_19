package com.univ.webService;

public class Abonent {
    private int idAbonent;
    private String Name;
    private String Surname;
    private String phoneNumber;
    private int idAreaCode;
    private int idBilling;
    private String Login;
    private String Password;
    private int isAdmin;

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public int getIdAbonent() {
        return idAbonent;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getIdAreaCode() {
        return idAreaCode;
    }

    public int getIdBilling() {
        return idBilling;
    }

    public void setIdAbonent(int idAbonent) {
        this.idAbonent = idAbonent;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setIdAreaCode(int idAreaCode) {
        this.idAreaCode = idAreaCode;
    }

    public void setIdBilling(int idBilling) {
        this.idBilling = idBilling;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
