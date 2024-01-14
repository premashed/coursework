package com.example.investmentclient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Класс для взаимосвязи с таблицей JavaFX
 */
public class TradingSessions {
    /**
     * Id
     */
    public StringProperty boardId;
    /**
     * Дата
     */
    public StringProperty tradeDate;
    /**
     * Короткое название
     */
    public StringProperty shortName;
    /**
     * Secid
     */
    public StringProperty secId;
    /**
     * Количество операций
     */
    public StringProperty numTrades;
    /**
     * Цена
     */
    public StringProperty value;
    /**
     * Цена на открытие
     */
    public StringProperty open;
    /**
     * Низшая цена
     */
    public StringProperty low;
    /**
     * Высшая цена
     */
    public StringProperty high;
    /**
     * Цена
     */
    public StringProperty warPrice;
    /**
     * Цена на закрытие
     */
    public StringProperty close;
    /**
     * Количество
     */
    public StringProperty volume;
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
    public TradingSessions(String boardId, String tradeDate, String shortName,
                           String secId, String numTrades, String value,
                           String open, String low, String high, String warPrice,
                           String close, String volume) {
        this.boardId = new SimpleStringProperty(this, "boradId", boardId);
        this.tradeDate = new SimpleStringProperty(this, "tradeDate", tradeDate);
        this.shortName = new SimpleStringProperty(this, "shortName", shortName);
        this.secId = new SimpleStringProperty(this, "secId", secId);
        this.numTrades = new SimpleStringProperty(this, "numTrades", numTrades);
        this.value = new SimpleStringProperty(this, "value", value);
        this.open = new SimpleStringProperty(this, "open", open);
        this.low = new SimpleStringProperty(this, "low", low);
        this.high = new SimpleStringProperty(this, "high", high);
        this.warPrice = new SimpleStringProperty(this, "warPrice", warPrice);
        this.close = new SimpleStringProperty(this, "close", close);
        this.volume = new SimpleStringProperty(this, "volume", volume);
    }

    public String getBoardId() {
        return boardId.get();
    }

    public StringProperty boardIdProperty() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId.set(boardId);
    }

    public String getTradeDate() {
        return tradeDate.get();
    }

    public StringProperty tradeDateProperty() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate.set(tradeDate);
    }

    public String getShortName() {
        return shortName.get();
    }

    public StringProperty shortNameProperty() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName.set(shortName);
    }

    public String getSecId() {
        return secId.get();
    }

    public StringProperty secIdProperty() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId.set(secId);
    }

    public String getNumTrades() {
        return numTrades.get();
    }

    public StringProperty numTradesProperty() {
        return numTrades;
    }

    public void setNumTrades(String numTrades) {
        this.numTrades.set(numTrades);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String getOpen() {
        return open.get();
    }

    public StringProperty openProperty() {
        return open;
    }

    public void setOpen(String open) {
        this.open.set(open);
    }

    public String getLow() {
        return low.get();
    }

    public StringProperty lowProperty() {
        return low;
    }

    public void setLow(String low) {
        this.low.set(low);
    }

    public String getHigh() {
        return high.get();
    }

    public StringProperty highProperty() {
        return high;
    }

    public void setHigh(String high) {
        this.high.set(high);
    }

    public String getWarPrice() {
        return warPrice.get();
    }

    public StringProperty warPriceProperty() {
        return warPrice;
    }

    public void setWarPrice(String warPrice) {
        this.warPrice.set(warPrice);
    }

    public String getClose() {
        return close.get();
    }

    public StringProperty closeProperty() {
        return close;
    }

    public void setClose(String close) {
        this.close.set(close);
    }

    public String getVolume() {
        return volume.get();
    }

    public StringProperty volumeProperty() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume.set(volume);
    }
}
