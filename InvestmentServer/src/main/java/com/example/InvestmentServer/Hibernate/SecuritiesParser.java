package com.example.InvestmentServer.Hibernate;

import com.example.InvestmentServer.Auction;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс предназначен для парсинга Мосбиржи и записи результатов торгов в БД
 */
public class SecuritiesParser {

    /**
     * Метод парсит с адреса https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/
     * итоги торгов в соответствии с переданным к нему идентификатором акции в указанный период времени
     * результат записывается в таблицу в БД посредством Hibernate
     *
     * @param stock передаем идентификатор акции
     * @param from  начало периода
     * @param till  конец периода
     * @throws IOException ошибка
     */
    public synchronized void getParser(String stock, String from, String till) throws IOException {
        String url = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/" + stock + ".json?from=" + from + "&till=" + till;

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
                    TradingSessions tradingSessions = new TradingSessions(
                            dataObject.get(0).getAsString(),
                            dataObject.get(1).getAsString(),
                            dataObject.get(2).getAsString(),
                            dataObject.get(3).getAsString(),
                            dataObject.get(4).getAsString(),
                            dataObject.get(5).getAsString(),
                            dataObject.get(6).getAsString(),
                            dataObject.get(7).getAsString(),
                            dataObject.get(8).getAsString(),
                            dataObject.get(10).getAsString(),
                            dataObject.get(11).getAsString(),
                            dataObject.get(12).getAsString()
                    );

                    Transaction transaction = null;
                    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                        // Старт транзакции
                        transaction = session.beginTransaction();
                        // Добавим в БД трэйдинг
                        session.persist(tradingSessions);
                        // Коммит транзакции
                        transaction.commit();
                    } catch (Exception e) {
                        if (transaction != null) {
                            transaction.rollback();
                        }
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Метод очищает таблицу в БД
     *
     * @param database задаем название таблицы
     */
    public synchronized void truncateTable(String database) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.createNativeQuery("truncate table " + database).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * @return метод возвращает с БД массив результаты торгов Ростелекома
     */
    public synchronized List<TradingSessions> getTradingSessions() {
        Transaction transaction = null;
        List<TradingSessions> trading = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            trading = session.createQuery("from TradingSessions", TradingSessions.class).list();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return trading;
    }

    /**
     * Метод записывает в таблице auctionsH результаты торгов
     *
     * @param auctionsData передаем результаты торгов портфеля
     */
    public synchronized void writeAuctions(List<Auction> auctionsData) {
        //Чистим таблицу перед записью
        truncateTable("auctions");

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД трэйдинг
            for (Auction auction : auctionsData) {
                Auctions auctions = new Auctions(auction.date, auction.close, auction.stock, auction.count);
                session.persist(auctions);
            }
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * @return возвращает массив результатов торгов с БД
     */
    public synchronized List<Auctions> getAuctionsH() {
        Transaction transaction = null;
        List<Auctions> auctionsList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            auctionsList = session.createQuery("from Auctions", Auctions.class).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println(auctionsList);
        return auctionsList;
    }

    /**
     * @return возвращает массив юзеров из БД
     */
    public synchronized List<User> getUsers() {
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            users = session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Метод заполнения таблицы users
     *
     * @param li список пользователей
     */
    public void fillUsers(List<User> li) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (User user : li) {
                session.persist(new User(user.getLogin(), user.getPassword()));
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Парсинг видов бумаг
     * @throws IOException ошибка
     */
    public synchronized void parseStockTypes() throws IOException {
        String url = "https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR.json";

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
            JsonObject historyObject = jsonObject.getAsJsonObject();
            historyObject = historyObject.getAsJsonObject("board");

            int length = historyObject.getAsJsonArray("data").size();
            for (int i = 0; i < length; i++) {
                JsonArray dataObject = historyObject.getAsJsonArray("data").get(i).getAsJsonArray();
                StockTypes stocktypes = new StockTypes(
                        dataObject.get(1).getAsString(),
                        dataObject.get(2).getAsString(),
                        dataObject.get(3).getAsString()
                );

                Transaction transaction = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    // Старт транзакции
                    transaction = session.beginTransaction();
                    // Добавим в БД трэйдинг
                    session.persist(stocktypes);
                    // Коммит транзакции
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Парсинг эмитентов
     * @throws IOException ошибка
     */
    public synchronized void parseEmitents() throws IOException {
        List<Emitent> li = new ArrayList<>();
        li.add(emitentHelper("SBER", "https://iss.moex.com/iss/securities.json?q=SBER"));
        li.add(emitentHelper("RTKM", "https://iss.moex.com/iss/securities.json?q=RTKM"));
        li.add(emitentHelper("LKOH","https://iss.moex.com/iss/securities.json?q=LKOH"));
        li.add(emitentHelper("GAZP","https://iss.moex.com/iss/securities.json?q=GAZP"));
        li.add(emitentHelper("MSNG","https://iss.moex.com/iss/securities.json?q=MSNG"));
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (Emitent emitent : li) {
                session.persist(emitent);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Парсинг эмитентов
     * @throws IOException ошибка
     * @param id id
     * @param url url
     * @return эмитент
     */
    public Emitent emitentHelper(String id, String url) throws IOException {
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
            JsonObject historyObject = jsonObject.getAsJsonObject();
            historyObject = historyObject.getAsJsonObject("securities");

            int length = historyObject.getAsJsonArray("data").size();
            for (int i = 0; i < length; i++) {
                JsonArray dataObject = historyObject.getAsJsonArray("data").get(i).getAsJsonArray();
                if (dataObject.get(1).getAsString().equals(id)) {
                    Emitent emitent = new Emitent(
                            dataObject.get(1).getAsString(),
                            dataObject.get(7).getAsString(),
                            dataObject.get(8).getAsString(),
                            dataObject.get(9).getAsString(),
                            dataObject.get(10).getAsString(),
                            dataObject.get(14).getAsString()
                    );
                    return emitent;
                }
            }
        }
        return null;
    }
}