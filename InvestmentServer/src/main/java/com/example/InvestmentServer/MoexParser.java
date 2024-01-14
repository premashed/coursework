package com.example.InvestmentServer;

import com.example.InvestmentServer.Hibernate.SecuritiesParser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс используется для обработки и расчета максимальной доходности портфеля
 */
public class MoexParser {
    /**
     * Парсер hibernate
     */
    SecuritiesParser securitiesParser = new SecuritiesParser();
    /**
     * Данные аукциона
     */
    public List<Auction> auctionsData = new ArrayList<>();
    /**
     * Ростелеком
     */
    public Stock rtkm = new Stock();
    /**
     * Мосэнерго
     */
    public Stock msng = new Stock();
    /**
     * Газпром
     */
    public Stock gazp = new Stock();
    /**
     * Лукойл
     */
    public Stock lkoh = new Stock();
    /**
     * Сбербанк
     */
    public Stock sber = new Stock();
    /**
     * Проценты
     */
    public String percentageG = new String();
    /**
     * Деньги
     */
    public String finalAmountG = new String();
    /**
     * Выручка
     */
    public String annualReturn = new String();

    /**
     * Метод записывает в массив итоги торгов за выбранный период времени, далее данные используются
     * для максимальной доходности портфеля
     * @param moneyFirst первоначальный взнос
     * @param from начало периода
     * @param till конец периода
     * @throws IOException ошибка
     */
    public void getParser(String moneyFirst, String from, String till) throws IOException {
        auctionsData.clear();

        rtkm.clearAll();
        msng.clearAll();
        gazp.clearAll();
        lkoh.clearAll();
        sber.clearAll();

        String boardId = "";
        double money_first = Double.parseDouble(moneyFirst);
        int count_of_stocks = 0;
        double beginning = money_first;
        double percentage = 0;
        double final_amount = 0;
        double annual_return = 0;

        ArrayList<String> stocks = new ArrayList<>();

        String urlRTKM = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/RTKM.json?from=" + from + "&till=" + till;
        String urlMSNG = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/MSNG.json?from=" + from + "&till=" + till;
        String urlGAZP = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/GAZP.json?from=" + from + "&till=" + till;
        String urlLKOH = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/LKOH.json?from=" + from + "&till=" + till;
        String urlSBER = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/SBER.json?from=" + from + "&till=" + till;

        stocks.add(urlRTKM);
        stocks.add(urlMSNG);
        stocks.add(urlGAZP);
        stocks.add(urlLKOH);
        stocks.add(urlSBER);

        for (String url : stocks) {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
                JsonObject historyObject = jsonObject.getAsJsonObject("history");

                int length = historyObject.getAsJsonArray("data").size();
                for (int i = 0; i < length; i++) {

                    JsonArray dataObject = historyObject.getAsJsonArray("data").get(i).getAsJsonArray();
                    if (dataObject.get(0).getAsString().equals("TQBR")) {
                        if (url.equals(urlMSNG)) {
                            msng.closing.add(Double.parseDouble(dataObject.get(11).getAsString()));
                            msng.date.add(dataObject.get(1).getAsString());
                        } else if (url.equals(urlGAZP)) {
                            gazp.closing.add(Double.parseDouble(dataObject.get(11).getAsString()));
                            gazp.date.add(dataObject.get(1).getAsString());
                        } else if (url.equals(urlRTKM)) {
                            rtkm.closing.add(Double.parseDouble(dataObject.get(11).getAsString()));
                            rtkm.date.add(dataObject.get(1).getAsString());
                        } else if (url.equals(urlLKOH)) {
                            lkoh.closing.add(Double.parseDouble(dataObject.get(11).getAsString()));
                            lkoh.date.add(dataObject.get(1).getAsString());
                        } else if (url.equals(urlSBER)) {
                            sber.closing.add(Double.parseDouble(dataObject.get(11).getAsString()));
                            sber.date.add(dataObject.get(1).getAsString());
                        }
                    }
                }
            } else {
                System.out.println("Request failed. Response Code: " + responseCode);
            }
        }
        msng.getDelta();
        rtkm.getDelta();
        lkoh.getDelta();
        gazp.getDelta();
        sber.getDelta();
        ArrayList<Double> list = new ArrayList<>();

        int length = msng.closing.size();

        for (int i = 0; i < length - 1; i++) {
            list.add(msng.delta.get(i));
            list.add(rtkm.delta.get(i));
            list.add(lkoh.delta.get(i));
            list.add(gazp.delta.get(i));
            list.add(sber.delta.get(i));
            Double max = Collections.max(list);
            int maxIndex = list.indexOf(max);
            if (i != 0) {
                if (boardId.equals("MSNG")) {
                    money_first = money_first + (count_of_stocks * msng.closing.get(i));
                    count_of_stocks = 0;
                } else if (boardId.equals("RTKM")) {
                    money_first = money_first + (count_of_stocks * rtkm.closing.get(i));
                    count_of_stocks = 0;
                } else if (boardId.equals("LKOH")) {
                    money_first = money_first + (count_of_stocks * lkoh.closing.get(i));
                    count_of_stocks = 0;
                } else if (boardId.equals("GAZP")) {
                    money_first = money_first + (count_of_stocks * gazp.closing.get(i));
                    count_of_stocks = 0;
                } else if (boardId.equals("SBER")) {
                    money_first = money_first + (count_of_stocks * sber.closing.get(i));
                    count_of_stocks = 0;
                }
            }
            if (count_of_stocks == 0) {
                if (maxIndex == 0) {
                    count_of_stocks = (int) (money_first / msng.closing.get(i));
                    money_first = money_first - (msng.closing.get(i) * count_of_stocks);
                    boardId = "MSNG";
                    auctionsData.add(new Auction(msng.date.get(i), String.valueOf(msng.closing.get(i)), "MSNG", String.valueOf(count_of_stocks)));
                    System.out.println("MSNG: " + count_of_stocks + " " + msng.date.get(i));
                } else if (maxIndex == 1) {
                    count_of_stocks = (int) (money_first / rtkm.closing.get(i));
                    money_first = money_first - (rtkm.closing.get(i) * count_of_stocks);
                    boardId = "RTKM";
                    auctionsData.add(new Auction(rtkm.date.get(i), String.valueOf(rtkm.closing.get(i)), "RTKM", String.valueOf(count_of_stocks)));
                    System.out.println("RTKM: " + count_of_stocks + " " + rtkm.date.get(i));
                } else if (maxIndex == 2) {
                    count_of_stocks = (int) (money_first / lkoh.closing.get(i));
                    money_first = money_first - (lkoh.closing.get(i) * count_of_stocks);
                    boardId = "LKOH";
                    auctionsData.add(new Auction(lkoh.date.get(i), String.valueOf(lkoh.closing.get(i)), "LKOH", String.valueOf(count_of_stocks)));
                    System.out.println("LKOH: " + count_of_stocks + " " + lkoh.date.get(i));
                } else if (maxIndex == 3) {
                    count_of_stocks = (int) (money_first / gazp.closing.get(i));
                    money_first = money_first - (gazp.closing.get(i) * count_of_stocks);
                    boardId = "GAZP";
                    auctionsData.add(new Auction(gazp.date.get(i), String.valueOf(gazp.closing.get(i)), "GAZP", String.valueOf(count_of_stocks)));
                    System.out.println("GAZP: " + count_of_stocks + " " + gazp.date.get(i));
                } else if (maxIndex == 4) {
                    count_of_stocks = (int) (money_first / sber.closing.get(i));
                    money_first = money_first - (sber.closing.get(i) * count_of_stocks);
                    boardId = "SBER";
                    auctionsData.add(new Auction(sber.date.get(i), String.valueOf(sber.closing.get(i)), "SBER", String.valueOf(count_of_stocks)));
                    System.out.println("SBER: " + count_of_stocks + " " + sber.date.get(i));
                }
            }
            if (i == length-2) {
                if (boardId.equals("MSNG")) {
                    money_first = money_first + (count_of_stocks * msng.closing.get(i));
                    count_of_stocks = 0;
                } else if (boardId.equals("RTKM")) {
                    money_first = money_first + (count_of_stocks * rtkm.closing.get(i));
                    count_of_stocks = 0;
                } else if (boardId.equals("LKOH")) {
                    money_first = money_first + (count_of_stocks * lkoh.closing.get(i));
                    count_of_stocks = 0;
                } else if (boardId.equals("GAZP")) {
                    money_first = money_first + (count_of_stocks * gazp.closing.get(i));
                    count_of_stocks = 0;
                } else if (boardId.equals("SBER")) {
                    money_first = money_first + (count_of_stocks * sber.closing.get(i));
                    count_of_stocks = 0;
                }
            }
            list.clear();
        }

        percentage = (money_first - beginning) / beginning;
        final_amount = money_first * Math.pow(1 + percentage, 12);
        annual_return = ((final_amount - beginning) / beginning) * 100;

        percentageG = String.valueOf(percentage * 100);
        finalAmountG = String.valueOf(final_amount);
        annualReturn = String.valueOf(annual_return);
        //Записываем в БД данные по торгам
        securitiesParser.writeAuctions(auctionsData);
    }
}

