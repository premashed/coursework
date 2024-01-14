package com.example.InvestmentServer.Hibernate;


import jakarta.persistence.*;

import java.util.List;

/**
 * Класс представляет таблицу users в БД stocks
 */
@Entity
@Table(name = "stocktypes")
public class StockTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "board_group_id")
    private String groupId;

    @Column(name = "boardid", unique = true)
    private String boardId;
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "primaryBoardId")
    private List<Emitent> emitentList;
    @OneToMany(mappedBy = "boardId")
    private List<TradingSessions> tradingSessionsList;

    /**
     * Пустой конструктор класса
     */
    public StockTypes(){}

    /**
     * Конструктор класса
     * @param groupId id группы
     * @param boardId сокращение
     * @param title вид акций
     */
    public StockTypes(String groupId, String boardId, String title){
        this.groupId =groupId;
        this.boardId =boardId;
        this.title=title;
    }
    /**
     * Получить сокращение
     * @return boardId сокращение
     */
    public String getBoardId() {
        return boardId;
    }
    /**
     * Получить id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Задать сокращение
     * @param boardId сокращение
     */
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
    /**
     * Получить id группы
     * @return id группы
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Задать id группы
     * @param groupId id группы
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    /**
     * Получить вид акций
     * @return вид акций
     */
    public String getTitle() {
        return title;
    }

    /**
     * Задать вид акций
     * @param title вид акций
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
