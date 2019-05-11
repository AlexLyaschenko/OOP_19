package com.univ.webService;

import com.univ.webService.DAO.BillingDAO;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) {
        String str = "123+=EFWFfeowi";
        if (str.matches("^[A-Za-z0-9]{1,35}")) {
            System.out.println("cool");
        }
    }
}
