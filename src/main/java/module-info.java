module com.example.restaurantul {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.restaurantul to javafx.fxml;
    exports com.example.restaurantul;

    exports com.example.restaurantul.controllers;
    opens com.example.restaurantul.controllers to javafx.fxml;

    exports com.example.restaurantul.domain;
    opens com.example.restaurantul.domain to javafx.fxml;

    exports com.example.restaurantul.repository;
    opens com.example.restaurantul.repository to javafx.fxml;

    exports com.example.restaurantul.service;
    opens com.example.restaurantul.service to javafx.fxml;
    exports com.example.restaurantul.repository.DBRepository;
    opens com.example.restaurantul.repository.DBRepository to javafx.fxml;
}