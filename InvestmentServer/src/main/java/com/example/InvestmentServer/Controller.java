package com.example.InvestmentServer;

import com.example.InvestmentServer.Hibernate.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки http - запросов от клиента
 */
@RestController
@RequestMapping
public class Controller {
    /**
     * API парсер
     */
    public MoexParser moexParser = new MoexParser();
    /**
     * Энкодер паролей spring boot
     */
    public final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Список акций
     */
    public List<TradingSessions> tradingSessionsStock = new ArrayList<>();
    /**
     * Портфель
     */
    public List<Auctions> auctionsList = new ArrayList<>();
    /**
     * Парсер
     */
    public SecuritiesParser securitiesParser = new SecuritiesParser();
    /**
     * Пользователи
     */
    public List<User> users = new ArrayList<>();
    /**
     * Авторизация
     */
    public boolean authorized;
    /**
     * Публичный класс controller при инициализации производится хеширование паролей
     * @throws IOException ошибка
     */
    public Controller() throws IOException {
        users.add(new User("root", "root"));
        users.add(new User("user", "user"));
        for (User user : users) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        securitiesParser.fillUsers(users);
        securitiesParser.parseStockTypes();
        securitiesParser.parseEmitents();
    }

    @GetMapping("/")
    String getMainPage() {
        return "Система учета результатов биржевых торгов по акциям, осуществляющая " +
                "по запросу расчет за период максимально возможной доходности " +
                "портфеля из выбранного набора акций";
    }
    @PostMapping("/login/")
    private void getAuthorized(@RequestBody User currentUser) {
        String login = currentUser.getLogin();
        String password = currentUser.getPassword();
        users = securitiesParser.getUsers();
        authorized = false;
        for (User user: users) {
            if (user.getLogin().equals(login) && passwordEncoder.matches(password, user.getPassword())) {
                authorized = true;
            } else {}
        }
    }
    @GetMapping("/login/")
    private String returnAuth() {
        if (authorized) {
            return "AUTHORIZED";
        } else {
            return "UNAUTHORIZED";
        }
    }
    @GetMapping("/tradesessions/{stock}&{from}&{till}")
    private List<TradingSessions> getTradingSessions(@PathVariable String stock, @PathVariable String from, @PathVariable String till) throws IOException {
        tradingSessionsStock.clear();
        securitiesParser.truncateTable("trading_sessions");
        securitiesParser.getParser(stock,from, till);
        tradingSessionsStock = securitiesParser.getTradingSessions();
        return tradingSessionsStock;
    }

    @GetMapping("/auctions/{moneyFirst}&{from}&{till}")
    private List<Auctions> getAuctions(@PathVariable String moneyFirst, @PathVariable String from, @PathVariable String till) throws IOException {
        auctionsList.clear();
        moexParser.getParser(moneyFirst, from, till);
        auctionsList = securitiesParser.getAuctionsH();
        return auctionsList;
    }
    @GetMapping("/getPercentage")
    private String getPercentage() {
        return moexParser.percentageG;
    }
    @GetMapping("/getFinalAmount")
    private String getFinalAmount() {
        return moexParser.finalAmountG;
    }
    @GetMapping("/getAnnualReturn")
    private String getAnnualReturn() {
        return moexParser.annualReturn;
    }
}
