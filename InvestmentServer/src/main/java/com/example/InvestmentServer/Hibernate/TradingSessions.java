package com.example.InvestmentServer.Hibernate;

import jakarta.persistence.*;

/**
 * Класс представляет таблицу trading_sessions_gazp в БД stocks
 */
@Entity
@Table(name = "trading_sessions")
public class TradingSessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "boardid")
    private String boardId;

    @Column(name = "tradedate")
    private String tradeDate;

    @Column(name = "shortname")
    private String shortName;

    @Column(name = "secid")
    private String secId;

    @Column(name = "numtrades")
    private String numTrades;

    @Column(name = "\"VALUE\"")
    private String value;

    @Column(name = "open")
    private String open;

    @Column(name = "low")
    private String low;

    @Column(name = "high")
    private String high;
    @ManyToOne
    @JoinColumn(name="secid",referencedColumnName = "secId",insertable=false, updatable=false)
    private Emitent emitent;
    @ManyToOne
    @JoinColumn(name="boardid",referencedColumnName = "boardid",insertable=false, updatable=false)
    private StockTypes stockTypes;
    @Column(name = "warprice")
    private String warPrice;

    @Column(name = "close")
    private String close;

    @Column(name = "volume")
    private String volume;
    /**
     * Пустой конструктор класса
     */
    public TradingSessions() {}
    /**
     * Конструктор класса
     * @param tradeDate дата
     * @param boardId id
     * @param shortName короткое название
     * @param secId secid
     * @param numTrades количество операций
     * @param value цена
     * @param open цена на открытие
     * @param low низшая цена
     * @param high высшая цена
     * @param warPrice цена
     * @param close цена на закрытие
     * @param volume количество
     */
    public TradingSessions(String boardId, String tradeDate, String shortName, String secId, String numTrades, String value, String open, String low, String high, String warPrice, String close, String volume) {
        this.boardId = boardId;
        this.tradeDate = tradeDate;
        this.shortName = shortName;
        this.secId = secId;
        this.numTrades = numTrades;
        this.value = value;
        this.open = open;
        this.low = low;
        this.high = high;
        this.warPrice = warPrice;
        this.close = close;
        this.volume = volume;
    }
    /**
     * Получить id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Задать id
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Получить id
     * @return id
     */
    public String getBoardId() {
        return boardId;
    }
    /**
     * Задать id
     * @param boardId id
     */
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
    /**
     * Получить дату
     * @return дату
     */
    public String getTradeDate() {
        return tradeDate;
    }
    /**
     * Задать дату
     * @param tradeDate дата
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    /**
     * Получить короткое название
     * @return короткое название
     */
    public String getShortName() {
        return shortName;
    }
    /**
     * Задать короткое название
     * @param shortName короткое название
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    /**
     * Получить sec id
     * @return sec id
     */
    public String getSecId() {
        return secId;
    }
    /**
     * Задать sec id
     * @param secId sec id
     */
    public void setSecId(String secId) {
        this.secId = secId;
    }
    /**
     * Получить количество операций
     * @return количество операций
     */
    public String getNumTrades() {
        return numTrades;
    }
    /**
     * Задать количество операций
     * @param numTrades количество операций
     */
    public void setNumTrades(String numTrades) {
        this.numTrades = numTrades;
    }
    /**
     * Получить цену
     * @return цена
     */
    public String getValue() {
        return value;
    }
    /**
     * Задать цену
     * @param value цена
     */
    public void setValue(String value) {
        this.value = value;
    }
    /**
     * Получить цену открытия
     * @return цена открытия
     */
    public String getOpen() {
        return open;
    }
    /**
     * Задать цену открытия
     * @param open цена открытия
     */
    public void setOpen(String open) {
        this.open = open;
    }
    /**
     * Получить низшую цену
     * @return низшая цена
     */
    public String getLow() {
        return low;
    }
    /**
     * Задать низшую цену
     * @param low низшая цена
     */
    public void setLow(String low) {
        this.low = low;
    }
    /**
     * Получить высшую цену
     * @return высшая цена
     */
    public String getHigh() {
        return high;
    }
    /**
     * Задать высшую цену
     * @param high высшая цена
     */
    public void setHigh(String high) {
        this.high = high;
    }
    /**
     * Получить цену
     * @return цена
     */
    public String getWarPrice() {
        return warPrice;
    }
    /**
     * Задать цену
     * @param warPrice цену
     */
    public void setWarPrice(String warPrice) {
        this.warPrice = warPrice;
    }
    /**
     * Получить цену закрытия
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
     * Получить количество
     * @return количество
     */

    public String getVolume() {
        return volume;
    }
    /**
     * Задать количество
     * @param volume  количество
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }
}
