module com.example.investmentclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires spring.web;

    opens com.example.investmentclient to com.google.gson, javafx.fxml;
    exports com.example.investmentclient;
}