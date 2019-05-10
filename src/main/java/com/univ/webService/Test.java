package com.univ.webService;

import com.univ.webService.DAO.BillingDAO;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(date));
        }
}
