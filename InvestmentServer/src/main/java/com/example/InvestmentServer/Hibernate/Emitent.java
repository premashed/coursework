package com.example.InvestmentServer.Hibernate;


import jakarta.persistence.*;

import java.util.List;

/**
 * Класс представляет таблицу users в БД stocks
 */
@Entity
@Table(name = "emitents")
public class Emitent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "secid", unique = true)
    private String secid;
    @Column(name = "emitent_id")
    private String emitentId;
    @Column(name = "emitent_title")
    private String emitentTitle;
    @Column(name = "emitent_inn")
    private String emitentInn;
    @Column(name = "emitent_okpo")
    private String emitentOkpo;
    @OneToMany(mappedBy = "secId")
    private List<TradingSessions> tradingSessionsList;
    @OneToMany(mappedBy = "stock")
    private List<Auctions> auctionsList;
    @Column(name = "primary_boardid")
    private String primaryBoardId;
    @ManyToOne
    @JoinColumn(name="primary_boardid",referencedColumnName = "boardid",insertable=false, updatable=false)
    private StockTypes stockTypes;

    /**
     * Пустой конструктор класса
     */
    public Emitent(){}

    /**
     * Конструктор класса
     * @param secid id акции
     * @param emitentId ID эммитента
     * @param emitentTitle название компании
     * @param emitentInn ИНН
     * @param emitentOkpo ОКПО
     * @param primaryBoardId вид акций
     */
    public Emitent(String secid, String emitentId,String emitentTitle, String emitentInn, String emitentOkpo,String primaryBoardId){
        this.secid= secid;
        this.emitentTitle=emitentTitle;
        this.emitentOkpo=emitentOkpo;
        this.emitentId=emitentId;
        this.emitentInn=emitentInn;
        this.primaryBoardId=primaryBoardId;
    }
}
