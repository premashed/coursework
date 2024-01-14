package com.example.investmentclient;
/**
 * Вспомогательный класс для передачи данных в таблицу JavaFX
 */
public class TradingSessionsTemp {
    /**
     * id
     */
    public String boardId;
    /**
     * Дата
     */
    public String tradeDate;
    /**
     * Короткое название
     */
    public String shortName;
    /**
     * Secid
     */
    public String secId;
    /**
     * Количество операций
     */
    public String numTrades;
    /**
     * Цена
     */
    public String value;
    /**
     * Цена на открытие
     */
    public String open;
    /**
     * Низшая цена
     */
    public String low;
    /**
     * Высшая цена
     */
    public String high;
    /**
     * Цена
     */
    public String warPrice;
    /**
     * Цена на закрытие
     */
    public String close;
    /**
     * Количество
     */
    public String volume;
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
    public TradingSessionsTemp(String boardId, String tradeDate, String shortName,
                               String secId, String numTrades, String value,
                               String open, String low, String high, String warPrice,
                               String close, String volume) {
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
}
