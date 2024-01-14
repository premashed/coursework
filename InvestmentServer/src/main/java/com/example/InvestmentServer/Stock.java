package com.example.InvestmentServer;

import java.util.ArrayList;

/**
 * Класс используется для хранения данных акций
 */
public class Stock {
    /**
     * Цена
     */
    public ArrayList<Double> closing = new ArrayList<>();
    /**
     * Даты
     */
    public ArrayList<String> date = new ArrayList<>();
    /**
     * Разница между ценами
     */
    public ArrayList<Double> delta = new ArrayList<>();

    /**
     * Метод возвращает массив разниц цен акции со следующим днем
     */
    public void getDelta() {
        int length = closing.size();
        for (int i = 0; i < length - 1; i++) {
            delta.add((closing.get(i + 1) - closing.get(i))/closing.get(i));
        }
    }
    /**
     * Метод чистки списков
     */
    public void clearAll() {
        closing.clear();
        date.clear();
        delta.clear();
    }
}
