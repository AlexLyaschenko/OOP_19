package com.univ.webService;


import com.univ.webService.DAO.AbonentDAO;
import com.univ.webService.DAO.HistoryDAO;
import com.univ.webService.DAO.PeopleInRegionDAO;
import com.univ.webService.DAO.PeopleInTariffDAO;
import com.univ.webService.dataModel.PeopleInTariff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Date date1 = new Date(100000);
        System.out.println(date1.getTime());
        String format = "yyyyMMdd";
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        PeopleInRegionDAO peopleInRegionDAO = new PeopleInRegionDAO();
        peopleInRegionDAO.setUserToDB(123,123,123);
    }
}
