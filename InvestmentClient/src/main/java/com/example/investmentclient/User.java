package com.example.investmentclient;
/**
 * Вспомогательный класс для передачи данных пользователя, имеет пустой конструктор
 */

public class User {
    private int id;
    private String login;
    private String password;
    /**
     * Пустой конструктор
     */
    public User(){}
    /**
     * Конструктор класса
     * @param login логин
     * @param password пароль
     */
    public User(String login, String password){
        this.login = login;
        this.password = password;
    }
    /**
     * Получить логин
     * @return id id
     */
    public String getLogin() {
        return login;
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
     * Задать логин
     * @param login логин
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * Получить пароль
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Задать пароль
     * @param password пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
