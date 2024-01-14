package com.example.investmentclient;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.web.client.RestTemplate;

/**
 * Класс приложения JavaFX
 */
public class MainApplication extends Application {
    @FXML
    private Button ok = new Button();
    @FXML
    private Button cancel = new Button();
    @FXML
    private TextField login = new TextField();
    @FXML
    private PasswordField password = new PasswordField();
    @FXML
    private void buttonOK() throws IOException {
        if (login.getText().matches("[a-zA-Z0-9]*") && password.getText().matches("[a-zA-Z0-9]*")) {
            String text = getAuthorized(login.getText(), password.getText());
            if (text.equals("AUTHORIZED")) {
                Stage stage = (Stage) ok.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 730, 400);
                stage.setScene(scene);
                stage.setTitle("Торги на бирже");
                stage.show();
            } else {
                System.out.println(text);
            }
        } else {
            System.out.println("Неправильные данные!");
        }
    }
    @FXML
    private void buttonCancel(){
        System.exit(0);
    }
    private String getAuthorized(String login, String password) {
            URL url = null;
            try {
                url = new URL("http://localhost:8080/login/");
                new RestTemplate().postForLocation("http://localhost:8080/login/", new User(login, password));
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                return in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("input.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 730, 400);
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Основной класс запуска приложение
     * @param args аргументы
     */
    public static void main(String[] args) {
        launch(args);
    }
}
