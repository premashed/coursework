package com.example.investmentclient;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.ParseException;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

/**
 * Класс-контроллер для формы main для входа просмотра таблиц
 */

public class Controller {
    //*****************************************************************************************
    //Вкладка Мосэнерго
    private ObservableList<TradingSessions> tradingSessions_data_msng = FXCollections.<TradingSessions>observableArrayList();
    @FXML
    private TableColumn<TradingSessions, String> boardId_msng = new TableColumn<TradingSessions, String>("BOARDID");
    @FXML
    private TableColumn<TradingSessions, String> tradeDate_msng = new TableColumn<TradingSessions, String>("TRADEDATE");
    @FXML
    private TableColumn<TradingSessions, String> shortName_msng = new TableColumn<TradingSessions, String>("SHORTNAME");
    @FXML
    private TableColumn<TradingSessions, String> secId_msng = new TableColumn<TradingSessions, String> ("SECID");
    @FXML
    private TableColumn<TradingSessions, String> numTrades_msng = new TableColumn<TradingSessions, String>("NUMTRADES");
    @FXML
    private TableColumn<TradingSessions, String> value_msng = new TableColumn<TradingSessions, String>("VALUE");
    @FXML
    private TableColumn<TradingSessions, String> open_msng = new TableColumn<TradingSessions, String>("OPEN");
    @FXML
    private TableColumn<TradingSessions, String> low_msng = new TableColumn<TradingSessions, String>("LOW");
    @FXML
    private TableColumn<TradingSessions, String> high_msng = new TableColumn<TradingSessions, String>("HIGH");
    @FXML
    private TableColumn<TradingSessions, String> warPrice_msng = new TableColumn<TradingSessions, String>("WARPRICE");
    @FXML
    private TableColumn<TradingSessions, String> close_msng = new TableColumn<TradingSessions, String>("CLOSE");
    @FXML
    private TableColumn<TradingSessions,String> volume_msng = new TableColumn<TradingSessions, String>("VOLUME");
    @FXML
    private TableView tableView_msng = new TableView();
    @FXML
    private TextField beginning_msng = new TextField();
    @FXML
    private TextField ending_msng = new TextField();
    @FXML
    private Button button_msng = new Button();

    /**
     * Метод получает с сервера JSON - сообщение и заполняет таблицу в fxml файле результаты торгов Мосэнерго
     */
    @FXML
    private void getTradeSessions_msng() throws ParseException {
        //Чистим таблицу перед обновлением
        tradingSessions_data_msng.clear();
        //Отправляем на сервер Get-запрос и получаем данные
        if (DateValidator.checkValidity(beginning_msng.getText()) && DateValidator.checkValidity(ending_msng.getText())) {
            String text = getTradeSessions("MSNG", beginning_msng.getText(), ending_msng.getText());
            //Используем библиотеку Gson, чтобы парсить объекты JSON
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            try {
                JsonArray jsonArray = jsonParser.parse(text).getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    //Помещаем jsonElement в класс TradingSessionsTemp(временный класс)
                    TradingSessionsTemp tradingSessionsTemp = gson.fromJson(jsonElement, TradingSessionsTemp.class);
                    //TradingSessionsTemp помещаем в класс TradingSessions
                    TradingSessions tradingSessions = new TradingSessions(
                            tradingSessionsTemp.boardId,
                            tradingSessionsTemp.tradeDate,
                            tradingSessionsTemp.shortName,
                            tradingSessionsTemp.secId,
                            tradingSessionsTemp.numTrades,
                            tradingSessionsTemp.value,
                            tradingSessionsTemp.open,
                            tradingSessionsTemp.low,
                            tradingSessionsTemp.high,
                            tradingSessionsTemp.warPrice,
                            tradingSessionsTemp.close,
                            tradingSessionsTemp.volume
                    );
                    //TradingSessions помещаем в массив tradingSessions_data_msng, другими словами, в таблицу для отображения данных
                    tradingSessions_data_msng.add(tradingSessions);

                    tableView_msng.setItems(tradingSessions_data_msng);
                    boardId_msng.setCellValueFactory(cellData -> cellData.getValue().boardIdProperty());
                    tradeDate_msng.setCellValueFactory(cellData -> cellData.getValue().tradeDateProperty());
                    shortName_msng.setCellValueFactory(cellData -> cellData.getValue().shortNameProperty());
                    secId_msng.setCellValueFactory(cellData -> cellData.getValue().secIdProperty());
                    numTrades_msng.setCellValueFactory(cellData -> cellData.getValue().numTradesProperty());
                    value_msng.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
                    open_msng.setCellValueFactory(cellData -> cellData.getValue().openProperty());
                    low_msng.setCellValueFactory(cellData -> cellData.getValue().lowProperty());
                    high_msng.setCellValueFactory(cellData -> cellData.getValue().highProperty());
                    warPrice_msng.setCellValueFactory(cellData -> cellData.getValue().warPriceProperty());
                    close_msng.setCellValueFactory(cellData -> cellData.getValue().closeProperty());
                    volume_msng.setCellValueFactory(cellData -> cellData.getValue().volumeProperty());
                }
            } catch (JsonSyntaxException e) {
            }
        }else {
            System.out.println("Неправильные данные!");
        }
    }
    //*****************************************************************************************

    //*****************************************************************************************
    //Вкладка Газпром
    private ObservableList<TradingSessions> tradingSessions_data_gazp = FXCollections.<TradingSessions>observableArrayList();
    @FXML
    private TableColumn<TradingSessions, String> boardId_gazp = new TableColumn<TradingSessions, String>("BOARDID");
    @FXML
    private TableColumn<TradingSessions, String> tradeDate_gazp = new TableColumn<TradingSessions, String>("TRADEDATE");
    @FXML
    private TableColumn<TradingSessions, String> shortName_gazp = new TableColumn<TradingSessions, String>("SHORTNAME");
    @FXML
    private TableColumn<TradingSessions, String> secId_gazp = new TableColumn<TradingSessions, String> ("SECID");
    @FXML
    private TableColumn<TradingSessions, String> numTrades_gazp = new TableColumn<TradingSessions, String>("NUMTRADES");
    @FXML
    private TableColumn<TradingSessions, String> value_gazp = new TableColumn<TradingSessions, String>("VALUE");
    @FXML
    private TableColumn<TradingSessions, String> open_gazp = new TableColumn<TradingSessions, String>("OPEN");
    @FXML
    private TableColumn<TradingSessions, String> low_gazp = new TableColumn<TradingSessions, String>("LOW");
    @FXML
    private TableColumn<TradingSessions, String> high_gazp = new TableColumn<TradingSessions, String>("HIGH");
    @FXML
    private TableColumn<TradingSessions, String> warPrice_gazp = new TableColumn<TradingSessions, String>("WARPRICE");
    @FXML
    private TableColumn<TradingSessions, String> close_gazp = new TableColumn<TradingSessions, String>("CLOSE");
    @FXML
    private TableColumn<TradingSessions,String> volume_gazp = new TableColumn<TradingSessions, String>("VOLUME");
    @FXML
    private TableView tableView_gazp = new TableView();
    @FXML
    private TextField beginning_gazp = new TextField();
    @FXML
    private TextField ending_gazp = new TextField();
    @FXML
    private Button button_gazp = new Button();

    /**
     * Метод получает с сервера JSON - сообщение и заполняет таблицу в fxml файле результаты торгов Газпрома
     */
    @FXML
    private void getTradeSessions_gazp() throws ParseException {
        //Чистим таблицу перед обновлением
        tradingSessions_data_gazp.clear();
        //Отправляем на сервер Get-запрос и получаем данные
        if (DateValidator.checkValidity(beginning_gazp.getText()) && DateValidator.checkValidity(ending_gazp.getText())) {
            String text = getTradeSessions("GAZP", beginning_gazp.getText(), ending_gazp.getText());
            //Используем библиотеку Gson, чтобы парсить объекты JSON
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            try {
                JsonArray jsonArray = jsonParser.parse(text).getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    //Помещаем jsonElement в класс TradingSessionsTemp(временный класс)
                    TradingSessionsTemp tradingSessionsTemp = gson.fromJson(jsonElement, TradingSessionsTemp.class);
                    //TradingSessionsTemp помещаем в класс TradingSessions
                    TradingSessions tradingSessions = new TradingSessions(
                            tradingSessionsTemp.boardId,
                            tradingSessionsTemp.tradeDate,
                            tradingSessionsTemp.shortName,
                            tradingSessionsTemp.secId,
                            tradingSessionsTemp.numTrades,
                            tradingSessionsTemp.value,
                            tradingSessionsTemp.open,
                            tradingSessionsTemp.low,
                            tradingSessionsTemp.high,
                            tradingSessionsTemp.warPrice,
                            tradingSessionsTemp.close,
                            tradingSessionsTemp.volume
                    );
                    //TradingSessions помещаем в массив tradingSessions_data_msng, другими словами, в таблицу для отображения данных
                    tradingSessions_data_gazp.add(tradingSessions);

                    tableView_gazp.setItems(tradingSessions_data_gazp);
                    boardId_gazp.setCellValueFactory(cellData -> cellData.getValue().boardIdProperty());
                    tradeDate_gazp.setCellValueFactory(cellData -> cellData.getValue().tradeDateProperty());
                    shortName_gazp.setCellValueFactory(cellData -> cellData.getValue().shortNameProperty());
                    secId_gazp.setCellValueFactory(cellData -> cellData.getValue().secIdProperty());
                    numTrades_gazp.setCellValueFactory(cellData -> cellData.getValue().numTradesProperty());
                    value_gazp.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
                    open_gazp.setCellValueFactory(cellData -> cellData.getValue().openProperty());
                    low_gazp.setCellValueFactory(cellData -> cellData.getValue().lowProperty());
                    high_gazp.setCellValueFactory(cellData -> cellData.getValue().highProperty());
                    warPrice_gazp.setCellValueFactory(cellData -> cellData.getValue().warPriceProperty());
                    close_gazp.setCellValueFactory(cellData -> cellData.getValue().closeProperty());
                    volume_gazp.setCellValueFactory(cellData -> cellData.getValue().volumeProperty());
                }
            } catch (JsonSyntaxException e) {
            }
        }else {
            System.out.println("Неправильные данные!");
        }
    }
    //*****************************************************************************************

    //*****************************************************************************************
    //Вкладка Ростелеком
    private ObservableList<TradingSessions> tradingSessions_data_rtkm = FXCollections.<TradingSessions>observableArrayList();
    @FXML
    private TableColumn<TradingSessions, String> boardId_rtkm = new TableColumn<TradingSessions, String>("BOARDID");
    @FXML
    private TableColumn<TradingSessions, String> tradeDate_rtkm = new TableColumn<TradingSessions, String>("TRADEDATE");
    @FXML
    private TableColumn<TradingSessions, String> shortName_rtkm = new TableColumn<TradingSessions, String>("SHORTNAME");
    @FXML
    private TableColumn<TradingSessions, String> secId_rtkm = new TableColumn<TradingSessions, String> ("SECID");
    @FXML
    private TableColumn<TradingSessions, String> numTrades_rtkm = new TableColumn<TradingSessions, String>("NUMTRADES");
    @FXML
    private TableColumn<TradingSessions, String> value_rtkm = new TableColumn<TradingSessions, String>("VALUE");
    @FXML
    private TableColumn<TradingSessions, String> open_rtkm = new TableColumn<TradingSessions, String>("OPEN");
    @FXML
    private TableColumn<TradingSessions, String> low_rtkm = new TableColumn<TradingSessions, String>("LOW");
    @FXML
    private TableColumn<TradingSessions, String> high_rtkm = new TableColumn<TradingSessions, String>("HIGH");
    @FXML
    private TableColumn<TradingSessions, String> warPrice_rtkm = new TableColumn<TradingSessions, String>("WARPRICE");
    @FXML
    private TableColumn<TradingSessions, String> close_rtkm = new TableColumn<TradingSessions, String>("CLOSE");
    @FXML
    private TableColumn<TradingSessions,String> volume_rtkm = new TableColumn<TradingSessions, String>("VOLUME");
    @FXML
    private TableView tableView_rtkm = new TableView();
    @FXML
    private TextField beginning_rtkm = new TextField();
    @FXML
    private TextField ending_rtkm = new TextField();
    @FXML
    private Button button_rtkm = new Button();

    /**
     * Метод получает с сервера JSON - сообщение и заполняет таблицу в fxml файле результаты торгов Ростелекома
     */

    @FXML
    private void getTradeSessions_rtkm() throws ParseException {
        tradingSessions_data_rtkm.clear();
        //Отправляем на сервер Get-запрос и получаем данные
        if (DateValidator.checkValidity(beginning_rtkm.getText()) && DateValidator.checkValidity(ending_rtkm.getText())) {
            String text = getTradeSessions("RTKM", beginning_rtkm.getText(), ending_rtkm.getText());
            //Используем библиотеку Gson, чтобы парсить объекты JSON
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            try {
                JsonArray jsonArray = jsonParser.parse(text).getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    //Помещаем jsonElement в класс TradingSessionsTemp(временный класс)
                    TradingSessionsTemp tradingSessionsTemp = gson.fromJson(jsonElement, TradingSessionsTemp.class);
                    //TradingSessionsTemp помещаем в класс TradingSessions
                    TradingSessions tradingSessions = new TradingSessions(
                            tradingSessionsTemp.boardId,
                            tradingSessionsTemp.tradeDate,
                            tradingSessionsTemp.shortName,
                            tradingSessionsTemp.secId,
                            tradingSessionsTemp.numTrades,
                            tradingSessionsTemp.value,
                            tradingSessionsTemp.open,
                            tradingSessionsTemp.low,
                            tradingSessionsTemp.high,
                            tradingSessionsTemp.warPrice,
                            tradingSessionsTemp.close,
                            tradingSessionsTemp.volume
                    );
                    System.out.println(tradingSessionsTemp);
                    //TradingSessions помещаем в массив tradingSessions_data_msng, другими словами, в таблицу для отображения данных
                    tradingSessions_data_rtkm.add(tradingSessions);

                    tableView_rtkm.setItems(tradingSessions_data_rtkm);
                    boardId_rtkm.setCellValueFactory(cellData -> cellData.getValue().boardIdProperty());
                    tradeDate_rtkm.setCellValueFactory(cellData -> cellData.getValue().tradeDateProperty());
                    shortName_rtkm.setCellValueFactory(cellData -> cellData.getValue().shortNameProperty());
                    secId_rtkm.setCellValueFactory(cellData -> cellData.getValue().secIdProperty());
                    numTrades_rtkm.setCellValueFactory(cellData -> cellData.getValue().numTradesProperty());
                    value_rtkm.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
                    open_rtkm.setCellValueFactory(cellData -> cellData.getValue().openProperty());
                    low_rtkm.setCellValueFactory(cellData -> cellData.getValue().lowProperty());
                    high_rtkm.setCellValueFactory(cellData -> cellData.getValue().highProperty());
                    warPrice_rtkm.setCellValueFactory(cellData -> cellData.getValue().warPriceProperty());
                    close_rtkm.setCellValueFactory(cellData -> cellData.getValue().closeProperty());
                    volume_rtkm.setCellValueFactory(cellData -> cellData.getValue().volumeProperty());
                }
            } catch (JsonSyntaxException e) {
            }
        }else {
            System.out.println("Неправильные данные!");
        }
    }
    //*****************************************************************************************

    //*****************************************************************************************
    //Вкладка СБЕР
    private ObservableList<TradingSessions> tradingSessions_data_sber = FXCollections.<TradingSessions>observableArrayList();
    @FXML
    private TableColumn<TradingSessions, String> boardId_sber = new TableColumn<TradingSessions, String>("BOARDID");
    @FXML
    private TableColumn<TradingSessions, String> tradeDate_sber = new TableColumn<TradingSessions, String>("TRADEDATE");
    @FXML
    private TableColumn<TradingSessions, String> shortName_sber = new TableColumn<TradingSessions, String>("SHORTNAME");
    @FXML
    private TableColumn<TradingSessions, String> secId_sber = new TableColumn<TradingSessions, String> ("SECID");
    @FXML
    private TableColumn<TradingSessions, String> numTrades_sber = new TableColumn<TradingSessions, String>("NUMTRADES");
    @FXML
    private TableColumn<TradingSessions, String> value_sber = new TableColumn<TradingSessions, String>("VALUE");
    @FXML
    private TableColumn<TradingSessions, String> open_sber = new TableColumn<TradingSessions, String>("OPEN");
    @FXML
    private TableColumn<TradingSessions, String> low_sber = new TableColumn<TradingSessions, String>("LOW");
    @FXML
    private TableColumn<TradingSessions, String> high_sber = new TableColumn<TradingSessions, String>("HIGH");
    @FXML
    private TableColumn<TradingSessions, String> warPrice_sber = new TableColumn<TradingSessions, String>("WARPRICE");
    @FXML
    private TableColumn<TradingSessions, String> close_sber = new TableColumn<TradingSessions, String>("CLOSE");
    @FXML
    private TableColumn<TradingSessions,String> volume_sber = new TableColumn<TradingSessions, String>("VOLUME");
    @FXML
    private TableView tableView_sber = new TableView();
    @FXML
    private TextField beginning_sber = new TextField();
    @FXML
    private TextField ending_sber = new TextField();
    @FXML
    private Button button_sber = new Button();

    /**
     * Метод получает с сервера JSON - сообщение и заполняет таблицу в fxml файле результаты торгов Сбера
     */
    @FXML
    private void getTradeSessions_sber() throws ParseException {
        //Чистим таблицу перед обновлением
        tradingSessions_data_sber.clear();
        //Отправляем на сервер Get-запрос и получаем данные
        if (DateValidator.checkValidity(beginning_sber.getText()) && DateValidator.checkValidity(ending_sber.getText())){
            String text = getTradeSessions("SBER", beginning_sber.getText(), ending_sber.getText());
            //Используем библиотеку Gson, чтобы парсить объекты JSON
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            try {
                JsonArray jsonArray = jsonParser.parse(text).getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    //Помещаем jsonElement в класс TradingSessionsTemp(временный класс)
                    TradingSessionsTemp tradingSessionsTemp = gson.fromJson(jsonElement, TradingSessionsTemp.class);
                    //TradingSessionsTemp помещаем в класс TradingSessions
                    TradingSessions tradingSessions = new TradingSessions(
                            tradingSessionsTemp.boardId,
                            tradingSessionsTemp.tradeDate,
                            tradingSessionsTemp.shortName,
                            tradingSessionsTemp.secId,
                            tradingSessionsTemp.numTrades,
                            tradingSessionsTemp.value,
                            tradingSessionsTemp.open,
                            tradingSessionsTemp.low,
                            tradingSessionsTemp.high,
                            tradingSessionsTemp.warPrice,
                            tradingSessionsTemp.close,
                            tradingSessionsTemp.volume
                    );
                    //TradingSessions помещаем в массив tradingSessions_data_msng, другими словами, в таблицу для отображения данных
                    tradingSessions_data_sber.add(tradingSessions);

                    tableView_sber.setItems(tradingSessions_data_sber);
                    boardId_sber.setCellValueFactory(cellData -> cellData.getValue().boardIdProperty());
                    tradeDate_sber.setCellValueFactory(cellData -> cellData.getValue().tradeDateProperty());
                    shortName_sber.setCellValueFactory(cellData -> cellData.getValue().shortNameProperty());
                    secId_sber.setCellValueFactory(cellData -> cellData.getValue().secIdProperty());
                    numTrades_sber.setCellValueFactory(cellData -> cellData.getValue().numTradesProperty());
                    value_sber.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
                    open_sber.setCellValueFactory(cellData -> cellData.getValue().openProperty());
                    low_sber.setCellValueFactory(cellData -> cellData.getValue().lowProperty());
                    high_sber.setCellValueFactory(cellData -> cellData.getValue().highProperty());
                    warPrice_sber.setCellValueFactory(cellData -> cellData.getValue().warPriceProperty());
                    close_sber.setCellValueFactory(cellData -> cellData.getValue().closeProperty());
                    volume_sber.setCellValueFactory(cellData -> cellData.getValue().volumeProperty());
                }
            } catch (JsonSyntaxException e) {
            }
        }else {
            System.out.println("Неправильные данные!");
        }
    }
    //*****************************************************************************************

    //*****************************************************************************************
    //Вкладка Лукойл
    private ObservableList<TradingSessions> tradingSessions_data_lkoh = FXCollections.<TradingSessions>observableArrayList();
    @FXML
    private TableColumn<TradingSessions, String> boardId_lkoh = new TableColumn<TradingSessions, String>("BOARDID");
    @FXML
    private TableColumn<TradingSessions, String> tradeDate_lkoh = new TableColumn<TradingSessions, String>("TRADEDATE");
    @FXML
    private TableColumn<TradingSessions, String> shortName_lkoh = new TableColumn<TradingSessions, String>("SHORTNAME");
    @FXML
    private TableColumn<TradingSessions, String> secId_lkoh = new TableColumn<TradingSessions, String> ("SECID");
    @FXML
    private TableColumn<TradingSessions, String> numTrades_lkoh = new TableColumn<TradingSessions, String>("NUMTRADES");
    @FXML
    private TableColumn<TradingSessions, String> value_lkoh = new TableColumn<TradingSessions, String>("VALUE");
    @FXML
    private TableColumn<TradingSessions, String> open_lkoh = new TableColumn<TradingSessions, String>("OPEN");
    @FXML
    private TableColumn<TradingSessions, String> low_lkoh = new TableColumn<TradingSessions, String>("LOW");
    @FXML
    private TableColumn<TradingSessions, String> high_lkoh = new TableColumn<TradingSessions, String>("HIGH");
    @FXML
    private TableColumn<TradingSessions, String> warPrice_lkoh = new TableColumn<TradingSessions, String>("WARPRICE");
    @FXML
    private TableColumn<TradingSessions, String> close_lkoh = new TableColumn<TradingSessions, String>("CLOSE");
    @FXML
    private TableColumn<TradingSessions,String> volume_lkoh = new TableColumn<TradingSessions, String>("VOLUME");
    @FXML
    private TableView tableView_lkoh = new TableView();
    @FXML
    private TextField beginning_lkoh = new TextField();
    @FXML
    private TextField ending_lkoh = new TextField();
    @FXML
    private Button button_lkoh = new Button();

    /**
     * Метод получает с сервера JSON - сообщение и заполняет таблицу в fxml файле результаты торгов Лукойла
     */
    @FXML
    private void getTradeSessions_lkoh() throws ParseException {
        //Чистим таблицу перед обновлением
        tradingSessions_data_lkoh.clear();
        //Отправляем на сервер Get-запрос и получаем данные
        if (DateValidator.checkValidity(beginning_lkoh.getText()) && DateValidator.checkValidity(ending_lkoh.getText())) {
            String text = getTradeSessions("LKOH", beginning_lkoh.getText(), ending_lkoh.getText());
            //Используем библиотеку Gson, чтобы парсить объекты JSON
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            try {
                JsonArray jsonArray = jsonParser.parse(text).getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    //Помещаем jsonElement в класс TradingSessionsTemp(временный класс)
                    TradingSessionsTemp tradingSessionsTemp = gson.fromJson(jsonElement, TradingSessionsTemp.class);
                    System.out.println(tradingSessionsTemp);
                    //TradingSessionsTemp помещаем в класс TradingSessions
                    TradingSessions tradingSessions = new TradingSessions(
                            tradingSessionsTemp.boardId,
                            tradingSessionsTemp.tradeDate,
                            tradingSessionsTemp.shortName,
                            tradingSessionsTemp.secId,
                            tradingSessionsTemp.numTrades,
                            tradingSessionsTemp.value,
                            tradingSessionsTemp.open,
                            tradingSessionsTemp.low,
                            tradingSessionsTemp.high,
                            tradingSessionsTemp.warPrice,
                            tradingSessionsTemp.close,
                            tradingSessionsTemp.volume
                    );
                    //TradingSessions помещаем в массив tradingSessions_data_msng, другими словами, в таблицу для отображения данных
                    tradingSessions_data_lkoh.add(tradingSessions);

                    tableView_lkoh.setItems(tradingSessions_data_lkoh);
                    boardId_lkoh.setCellValueFactory(cellData -> cellData.getValue().boardIdProperty());
                    tradeDate_lkoh.setCellValueFactory(cellData -> cellData.getValue().tradeDateProperty());
                    shortName_lkoh.setCellValueFactory(cellData -> cellData.getValue().shortNameProperty());
                    secId_lkoh.setCellValueFactory(cellData -> cellData.getValue().secIdProperty());
                    numTrades_lkoh.setCellValueFactory(cellData -> cellData.getValue().numTradesProperty());
                    value_lkoh.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
                    open_lkoh.setCellValueFactory(cellData -> cellData.getValue().openProperty());
                    low_lkoh.setCellValueFactory(cellData -> cellData.getValue().lowProperty());
                    high_lkoh.setCellValueFactory(cellData -> cellData.getValue().highProperty());
                    warPrice_lkoh.setCellValueFactory(cellData -> cellData.getValue().warPriceProperty());
                    close_lkoh.setCellValueFactory(cellData -> cellData.getValue().closeProperty());
                    volume_lkoh.setCellValueFactory(cellData -> cellData.getValue().volumeProperty());
                }
            } catch (JsonSyntaxException e) {
            }
        }else {
            System.out.println("Неправильные данные!");
        }
    }
    //*****************************************************************************************

    private ObservableList<Auction> auctionsData = FXCollections.<Auction>observableArrayList();
    @FXML
    private TableColumn<Auction, String> date = new TableColumn<Auction, String>("Дата");
    @FXML
    private TableColumn<Auction, String> close = new TableColumn<Auction, String>("Закрытие");
    @FXML
    private TableColumn<Auction, String> stock = new TableColumn<Auction, String>("Акция");
    @FXML
    private TableColumn<Auction, String> count = new TableColumn<Auction, String>("Количество");
    @FXML
    private TableView tableView = new TableView();
    @FXML
    private TextField money_first = new TextField();
    @FXML
    private TextField beginning = new TextField();
    @FXML
    private TextField ending = new TextField();
    /**
     * Текстовое поле проценты
     */
    @FXML
    public TextField percentage = new TextField();
    /**
     * Текстовое поле деньги
     */
    @FXML
    public TextField final_amount = new TextField();
    /**
     * Текстовое поле выручка
     */
    @FXML
    public TextField annual_return = new TextField();
    @FXML
    private Button buttonGetProfitability = new Button();

    /**
     * Метод получает с сервера JSON - сообщение и заполняет таблицу в fxml файле результаты максимальной доходности портфеля
     * @throws IOException
     */
    @FXML
    private void getProfitability() throws IOException, ParseException {
        percentage.clear();
        final_amount.clear();
        annual_return.clear();

        auctionsData.clear();
        //Отправляем на сервер Get-запрос и получаем данные
        if (DateValidator.checkDiffrence(beginning.getText(), ending.getText()) && (money_first.getText().matches("[0-9]+") && Integer.parseInt(money_first.getText()) > 0)) {
            String text = getActions(money_first.getText(), beginning.getText(), ending.getText());

            //Используем библиотеку Gson, чтобы парсить объекты JSON
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            try {
                JsonArray jsonArray = jsonParser.parse(text).getAsJsonArray();
                for (JsonElement jsonElement: jsonArray) {
                    //Помещаем jsonElement в класс AuctionTemp(временный класс)
                    AuctionTemp auctionTemp = gson.fromJson(jsonElement, AuctionTemp.class);
                    //AuctionTemp помещаем в класс auction
                    Auction auction = new Auction(auctionTemp.id,
                            auctionTemp.date,
                            auctionTemp.close,
                            auctionTemp.stock,
                            auctionTemp.count
                    );
                    //Auction помещаем в массив auctionsData, другими словами, в таблицу для отображения данных
                    auctionsData.add(auction);

                    tableView.setItems(auctionsData);
                    date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
                    close.setCellValueFactory(cellData -> cellData.getValue().closeProperty());
                    stock.setCellValueFactory(cellData -> cellData.getValue().stockProperty());
                    count.setCellValueFactory(cellData -> cellData.getValue().countProperty());
                    percentage.setText(getPercentage());
                    final_amount.setText(getFinalAmount());
                    annual_return.setText(getAnnualReturn());
                }
            } catch (JsonSyntaxException e){
                e.printStackTrace();
            }
        }else {
            System.out.println("Неправильные данные!");
        }

    }

     /**
     *
     * @param moneyFirst передаем стартовый капитал
     * @param from передаем начало периода
     * @param till передаем конец периода
     * @return возвращает в формате JSON сообщение с сервера, данные с БД
     */
    private String getActions(String moneyFirst, String from, String till) {
        if (moneyFirst.isEmpty() || from.isEmpty() || till.isEmpty()) {
            return "You specified an invalid value";
        } else {
            URL url = null;
            try {
                url = new URL("http://localhost:8080/auctions/" + moneyFirst + "&" + from + "&" + till);
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                return in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     *
     * @param stock передаем идентификатор акции
     * @param from передаем начало периода
     * @param till передаем конец периода
     * @return возвращает в формате JSON сообщение с сервера, данные с БД
     */
    private String getTradeSessions(String stock, String from, String till) {
        if (stock.isEmpty() || from.isEmpty() || till.isEmpty()) {
            return "You specified an invalid value";
        } else {
            URL url = null;
            try {
                url = new URL("http://localhost:8080/tradesessions/" + stock + "&" + from + "&" + till);
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                return in.readLine();
            } catch (IOException e) {
                return "Server is not available";
            }
        }
    }
    /**
     *
     * @return возвращает с сервера месячный процент
     */
    private String getPercentage() {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/getPercentage");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return in.readLine();

        } catch (IOException e) {
            return "The server is not available";
        }
    }

    /**
     *
     * @return возвращает с сервера годовую доходность
     */
    private String getAnnualReturn() {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/getAnnualReturn");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return in.readLine();

        } catch (IOException e) {
            return "The server is not available";
        }
    }

    /**
     *
     * @return возвращает с сервера конечную сумму
     */
    private String getFinalAmount() {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/getFinalAmount");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return in.readLine();

        } catch (IOException e) {
            return "The server is not available";
        }
    }
}