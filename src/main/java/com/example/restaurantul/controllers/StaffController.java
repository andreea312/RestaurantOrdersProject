package com.example.restaurantul.controllers;

import com.example.restaurantul.domain.MenuItem;
import com.example.restaurantul.domain.Order;
import com.example.restaurantul.domain.OrderItem;
import com.example.restaurantul.service.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.List;

public class StaffController {
    Service service;

    @FXML
    HashMap<Order, List<MenuItem>> menuItemsByOrder = new HashMap<>();
    @FXML
    ObservableList<Order> ordersList;

    @FXML
    TableView<Order> tablePlacedOrders;
    @FXML
    TableColumn<Order, String> orderingTableColumn;
    @FXML
    TableColumn<Order, String> orderDateColumn;
    @FXML
    TableColumn<Order, String> orderedItemsColumn;

    public void setOrdersList(ObservableList<Order> ordersList){
        this.ordersList = ordersList;
    }
    public void setService(Service service) {
        this.service = service;
        menuItemsByOrder.putAll(service.getMenuItemsByOrder());
        for(Order order : ordersList){
            System.out.println(order.toString());
        }
        initializeaza();
    }

    public void initializeaza() {
        orderingTableColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("tableId"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order,String>("date"));

        orderedItemsColumn.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> p) {
                    int orderId = p.getValue().getId();
                    String str = "";
                    for(OrderItem orderItem : service.getAllOrderItems()){
                        if(orderItem.getOrderId() == orderId) {
                            String item = service.findMenuItem(orderItem.getMenuItemId()).getItem();
                            str = str + item + ", ";
                        }
                    }
                    return new SimpleStringProperty(str);
                }
            }
        );

        tablePlacedOrders.getColumns().clear();
        tablePlacedOrders.getColumns().add(orderingTableColumn);
        tablePlacedOrders.getColumns().add(orderDateColumn);
        tablePlacedOrders.getColumns().add(orderedItemsColumn);

        tablePlacedOrders.setItems(ordersList);

        orderDateColumn.setSortType(TableColumn.SortType.ASCENDING);
        tablePlacedOrders.getSortOrder().add(orderDateColumn);
        tablePlacedOrders.sort();
    }
}