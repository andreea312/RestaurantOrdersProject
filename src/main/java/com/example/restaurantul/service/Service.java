package com.example.restaurantul.service;


import com.example.restaurantul.domain.MenuItem;
import com.example.restaurantul.domain.Order;
import com.example.restaurantul.domain.OrderItem;
import com.example.restaurantul.domain.Table;
import com.example.restaurantul.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Service {
    Repository<Integer, Table> tableRepo;
    Repository<Integer, MenuItem> menuItemRepo;
    Repository<Integer, Order> ordersRepo;
    Repository<Integer, OrderItem> orderItemsRepo;

    public Service(Repository<Integer, Table> tableRepo,
                   Repository<Integer, MenuItem> menuItemRepo,
                   Repository<Integer, Order> ordersRepo,
                   Repository<Integer, OrderItem> orderItemsRepo){
        this.tableRepo = tableRepo;
        this.menuItemRepo = menuItemRepo;
        this.ordersRepo = ordersRepo;
        this.orderItemsRepo = orderItemsRepo;
    }

    public Iterable<Table> getAllTables(){
        return tableRepo.getAll();
    }
    public Iterable<MenuItem> getAllMenuItems(){
        return menuItemRepo.getAll();
    }

    public Iterable<Order> getAllOrders(){
        return ordersRepo.getAll();
    }

    public int generateIdOrder(){
        int max = 0;
        for(Order order: getAllOrders()) {
            if(order.getId() > max)
                max = order.getId();
        }
        return max + 1;
    }
    public void addOrder(Order order){
        ordersRepo.add(order);
    }

    public int generateIdOrderItem(){
        int max = 0;
        for(OrderItem orderItem: getAllOrderItems()) {
            if(orderItem.getId() > max)
                max = orderItem.getId();
        }
        return max + 1;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItemsRepo.add(orderItem);
    }

    public Iterable<OrderItem> getAllOrderItems(){return orderItemsRepo.getAll();}

    public List<String> getAllCategories(){
        List<String> allCategories = new ArrayList<>();
        for(MenuItem mi: getAllMenuItems()){
            allCategories.add(mi.getCategory());
        }
        return allCategories.stream().distinct().toList();
    }
    public HashMap<String, List<MenuItem>> getAllItemsByCategories(){
        HashMap<String, List<MenuItem>> hashmap = new HashMap<>();
        for(String category: getAllCategories()){
            List<MenuItem> lista = new ArrayList<>();
            for(MenuItem mi: getAllMenuItems()){
                if(mi.getCategory().equals(category))
                    lista.add(mi);
            }
            hashmap.put(category, lista);
        }
        return hashmap;
    }

    public HashMap<Order, List<MenuItem>> getMenuItemsByOrder(){
        HashMap<Order, List<MenuItem>> hashmap = new HashMap<>();
        for(Order order: getAllOrders()){
            List<MenuItem> lista = new ArrayList<>();
            for(OrderItem orderItem: getAllOrderItems()){
                if(orderItem.getOrderId() == order.getId())
                    lista.add(findMenuItem(orderItem.getId()));
            }
            hashmap.put(order, lista);
        }
        return hashmap;
    }

    public MenuItem findMenuItem(int id){
        return menuItemRepo.find(id);
    }
}
