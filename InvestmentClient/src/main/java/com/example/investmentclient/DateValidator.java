package com.example.investmentclient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Класс для валидации дат в формах приложения JavaFX
 */
public class DateValidator {
    /**
     * Класс валидации конкретной даты
     * @param date дата
     * @return правильно ли заполнена дата
     *@throws ParseException ошибка парсинга
     */
    public static boolean checkValidity(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        Date checkDate = sdf.parse("2023-12-31");
        if (sdf.parse(date).before(checkDate)) {
            return true;
        }
        return false;
    }
    /**
     * Класс валидации 2 дат
     * @param date2 дата 2
     * @param date1 дата 1
     * @return есть ли между датами месяц или меньше
     * @throws ParseException ошибка парсинга
     */
    public static boolean checkDiffrence(String date1, String date2) throws ParseException {
        if (checkValidity(date1) && checkValidity(date2)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date firstDate = sdf.parse(date1);
            Date secondDate = sdf.parse(date2);
            long milliDifference = Math.abs(secondDate.getTime() - firstDate.getTime());
            long differenceInDays = TimeUnit.DAYS.convert(milliDifference, TimeUnit.MILLISECONDS);
            return differenceInDays <= 31 && differenceInDays >0;
        }
        return false;
    }
}
