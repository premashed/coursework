package com.example.InvestmentServer.Hibernate;

import jakarta.persistence.*;

/**
 * Класс представляет таблицу auctions_h в БД stocks
 */
@Entity
@Table(name = "auctions")
public class Auctions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private String date;

    @Column(name = "close")
    private String close;

    @Column(name = "stock")
    private String stock;
    @ManyToOne
    @JoinColumn(name="stock",referencedColumnName = "secId",insertable=false, updatable=false)
    private Emitent emitent;
    @Column(name = "count")
    private String count;

    /**
     * Пустой конструктор класса
     */
    public Auctions() {
    }
    /**
     * Конструктор класса
     * @param count количество
     * @param date дата
     * @param close цена на закрытие
     * @param stock акция
     */
    public Auctions(String date, String close, String stock, String count) {
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
        this.close = close;
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
        this.stock = stock;
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
     * @param count количество
     */
    public void setCount(String count) {
        this.count = count;
    }
}
