package com.example.InvestmentServer;

/**
 * Класс для хранения итогов торгов
 */
public class Auction {
    /**
     * Id
     */
    public String id;
    /**
     * Дата
     */
    public String date;
    /**
     * Цена на закрытие
     */
    public String close;
    /**
     * Акция
     */
    public String stock;
    /**
     * Количество
     */
    public String count;

    /**
     * Конструктор класса
     * @param date дата
     * @param id id
     * @param count колличество
     * @param stock акция
     * @param close цена на закрытие
     */
    public Auction(String id, String date, String close, String stock, String count) {
        this.id = id;
        this.date = date;
        this.close = close;
        this.stock = stock;
        this.count = count;
    }
    /**
     * Конструктор класса
     * @param date дата
     * @param count колличество
     * @param stock акция
     * @param close цена на закрытие
     */
    public Auction(String date, String close, String stock, String count) {
        this.date = date;
        this.close = close;
        this.stock = stock;
        this.count = count;
    }
    /**
     * Получить дату
     * @return дата
     */
    public String getDate() {
        return date;
    }

    /**
     * Задать дату
     * @param date дата
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * Поучить цену закрытия
     * @return цена закрытия
     */
    public String getClose() {
        return close;
    }
    /**
     * Задать цену закрытия
     * @param close цена закрытия
     */
    public void setClose(String close) {
        this.close =close;
    }
    /**
     * Поучить акцию
     * @return акция
     */
    public String getStock() {
        return stock;
    }
    /**
     * Задать акцию
     * @param stock акция
     */
    public void setStock(String stock) {
        this.stock =stock;
    }
    /**
     * Поучить количество
     * @return количество
     */
    public String getCount() {
        return count;
    }
    /**
     * Задать количество
     * @param count  количество
     */
    public void setCount(String count) {
        this.count =count;
    }
}
