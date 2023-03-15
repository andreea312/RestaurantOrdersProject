package com.example.restaurantul.repository.factory;

import com.example.restaurantul.repository.DBRepository.MenuItemDBRepository;
import com.example.restaurantul.repository.DBRepository.OrderItemsDBRepository;
import com.example.restaurantul.repository.DBRepository.OrdersDBRepository;
import com.example.restaurantul.repository.DBRepository.TableDBRepository;

public class FactoryDBRepository {
    private static FactoryDBRepository instance = new FactoryDBRepository();

    public TableDBRepository createTableRepository() {
        return new TableDBRepository("jdbc:postgresql://localhost:5432/restaurantul", "postgres", "postgres");
    }
    public MenuItemDBRepository createMenuItemRepository() {
        return new MenuItemDBRepository("jdbc:postgresql://localhost:5432/restaurantul", "postgres", "postgres");
    }
    public OrdersDBRepository createOrdersRepository() {
        return new OrdersDBRepository("jdbc:postgresql://localhost:5432/restaurantul", "postgres", "postgres");
    }
    public OrderItemsDBRepository createOrderItemsRepository() {
        return new OrderItemsDBRepository("jdbc:postgresql://localhost:5432/restaurantul", "postgres", "postgres");
    }
}
