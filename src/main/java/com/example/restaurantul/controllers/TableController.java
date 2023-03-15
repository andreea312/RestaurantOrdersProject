package com.example.restaurantul.controllers;

import com.example.restaurantul.domain.*;
import com.example.restaurantul.domain.MenuItem;
import com.example.restaurantul.service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableController {
    Service service;

    Table table;
    @FXML
    ObservableList<MenuItem> menuObservableList = FXCollections.observableArrayList();
    @FXML
    HashMap<String, List<MenuItem>> itemsByCategories = new HashMap<>();

    @FXML
    List<TableView<MenuItem>> tableViewsList = new ArrayList<>();

    @FXML
    ObservableList<MenuItem> orderedItems = FXCollections.observableArrayList();

    @FXML
    ObservableList<Order> ordersList;

    @FXML
    HBox hBox = new HBox();
    @FXML
    Button buttonPlaceOrder = new Button("Place order");
    @FXML
    VBox vBox = new VBox();

    public void setTable(Table table) {
        this.table = table;
    }
    public void setOrdersList(ObservableList<Order> ordersList){
        this.ordersList = ordersList;
    }
    public void setService(Service service) {
        this.service = service;
        ArrayList<MenuItem> lista = (ArrayList<MenuItem>) service.getAllMenuItems();
        menuObservableList.addAll(lista);
        itemsByCategories.putAll(service.getAllItemsByCategories());
        initializeazaVBox();
    }

    public void initializeazaVBox() {
        itemsByCategories.forEach((category,list) ->{
            TableView<MenuItem> tableMenu = new TableView<>();
            tableMenu.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tableMenu.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<MenuItem, String> itemMenuItemColumn = new TableColumn<>("Item");
            TableColumn<MenuItem, String> priceCurrencyMenuItemColumn = new TableColumn<>("Price");
            tableMenu.getColumns().clear();

            itemMenuItemColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("item"));
            priceCurrencyMenuItemColumn.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MenuItem,String>, ObservableValue<String>>(){
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MenuItem, String> p){
                            MenuItem mi = p.getValue();
                            String str = String.valueOf(mi.getPrice()) + " " + mi.getCurrency();
                            return new SimpleStringProperty(str);
                        }
                    }
            );
            ObservableList<MenuItem> observableList = FXCollections.observableArrayList();
            observableList.setAll(list);
            tableMenu.setItems(observableList);
            tableMenu.getColumns().add(itemMenuItemColumn);
            tableMenu.getColumns().add(priceCurrencyMenuItemColumn);

            Label categoryLabel = new Label(category);
            vBox.getChildren().add(categoryLabel);
            vBox.getChildren().add(tableMenu);

            tableViewsList.add(tableMenu);
        });
    }

    public void pressButtonPlaceOrder() {
        for(TableView<MenuItem> tableView: tableViewsList){
            ArrayList<MenuItem> itemsPerTableView = new ArrayList<>(tableView.getSelectionModel().getSelectedItems());
            orderedItems.addAll(itemsPerTableView);
        }
        if(orderedItems.size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty order!");
            alert.setContentText("No menu item selected!!!");
            alert.showAndWait();
        }
        else {
            Order order = new Order(service.generateIdOrder(), this.table.getId(), LocalDateTime.now(), OrderStatus.PLACED);
            service.addOrder(order);
            ordersList.add(order);
            for (MenuItem menuItem : orderedItems) {
                OrderItem orderItem = new OrderItem(service.generateIdOrderItem(), order.getId(), menuItem.getId());
                service.addOrderItem(orderItem);
            }
        }
    }
}
