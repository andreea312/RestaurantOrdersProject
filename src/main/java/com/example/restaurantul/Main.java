package com.example.restaurantul;

import com.example.restaurantul.controllers.StaffController;
import com.example.restaurantul.controllers.TableController;
import com.example.restaurantul.domain.MenuItem;
import com.example.restaurantul.domain.Order;
import com.example.restaurantul.domain.OrderItem;
import com.example.restaurantul.domain.Table;
import com.example.restaurantul.repository.Repository;
import com.example.restaurantul.repository.factory.FactoryDBRepository;
import com.example.restaurantul.service.Service;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @FXML
    ObservableList<Order> ordersList = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws IOException {
        FactoryDBRepository factory = new FactoryDBRepository();
        Repository<Integer, Table> tableRepo = factory.createTableRepository();
        Repository<Integer, MenuItem> menuItemRepo = factory.createMenuItemRepository();
        Repository<Integer, Order> ordersRepo = factory.createOrdersRepository();
        Repository<Integer, OrderItem> orderItemsRepo = factory.createOrderItemsRepository();
        Service service = new Service(tableRepo, menuItemRepo, ordersRepo, orderItemsRepo);

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("StaffView.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        StaffController ctrl = loader.getController();
        ArrayList<Order> lista = (ArrayList<Order>) service.getAllOrders();
        ordersList.addAll(lista);
        ctrl.setOrdersList(ordersList);
        ctrl.setService(service);

        stage.setTitle("Staff");
        stage.setScene(scene);
        stage.show();

        service.getAllTables().forEach(table -> {
            Stage stageTable = new Stage();
            Scene sceneTable;
            FXMLLoader loaderTable = new FXMLLoader(Main.class.getResource("TableView.fxml"));
            try {
                sceneTable = new Scene(loaderTable.load(), 450, 450);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TableController ctrlTable = loaderTable.getController();
            ctrlTable.setTable(table);
            ctrlTable.setOrdersList(ordersList);
            ctrlTable.setService(service);

            stageTable.setTitle(table.toString());
            stageTable.setScene(sceneTable);
            stageTable.show();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}