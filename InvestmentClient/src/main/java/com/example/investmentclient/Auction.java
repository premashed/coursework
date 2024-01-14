package com.example.investmentclient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Класс для взаимосвязи с таблицей JavaFX
 */
public class Auction {
    /**
     * Id
     */
    public StringProperty id;
    /**
     * Дата
     */
    public StringProperty date;
    /**
     * Цена на закрытие
     */
    public StringProperty close;
    /**
     * Акция
     */
    public StringProperty stock;
    /**
     * Количество
     */
    public StringProperty count;
    /**
     * Конструктор класса
     * @param date дата
     * @param id id
     * @param count колличество
     * @param stock акция
     * @param close цена на закрытие
     */
    public Auction(String id, String date, String close, String stock, String count) {
        this.id = new SimpleStringProperty(this, "id", date);
        this.date = new SimpleStringProperty(this, "date", date);
        this.close = new SimpleStringProperty(this, "close", close);
        this.stock = new SimpleStringProperty(this, "stock", stock);
        this.count = new SimpleStringProperty(this, "count", count);
    }
    /**
     * Получить дату
     * @return дата
     */
    public String getDate() {
        return date.get();
    }
    /**
     * Получить дату
     * @return дата
     */
    public StringProperty dateProperty() {
        return date;
    }
    /**
     * Задать дату
     * @param date дата
     */
    public void setDate(String date) {
        this.date.set(date);
    }
    /**
     * Поучить цену закрытия
     * @return цена закрытия
     */
    public String getClose() {
        return close.get();
    }
    /**
     * Поучить цену закрытия
     * @return цена закрытия
     */
    public StringProperty closeProperty() {
        return close;
    }
    /**
     * Задать цену закрытия
     * @param close цена закрытия
     */
    public void setClose(String close) {
        this.close.set(close);
    }
    /**
     * Поучить акцию
     * @return акция
     */
    public String getStock() {
        return stock.get();
    }
    /**
     * Поучить акцию
     * @return акция
     */
    public StringProperty stockProperty() {
        return stock;
    }
    /**
     * Задать акцию
     * @param stock акция
     */
    public void setStock(String stock) {
        this.stock.set(stock);
    }
    /**
     * Поучить количество
     * @return количество
     */
    public String getCount() {
        return count.get();
    }
    /**
     * Поучить количество
     * @return количество
     */
    public StringProperty countProperty() {
        return count;
    }
    /**
     * Задать количество
     * @param count количество
     */
    public void setCount(String count) {
        this.count.set(count);
    }
}
