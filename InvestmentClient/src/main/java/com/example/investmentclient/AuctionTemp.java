package com.example.investmentclient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Вспомогательный класс для передачи данных в таблицу JavaFX
 */
public class AuctionTemp {
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
    public AuctionTemp(String id, String date, String close, String stock, String count) {
        this.id = id;
        this.date = date;
        this.close = close;
        this.stock = stock;
        this.count = count;
    }
}
