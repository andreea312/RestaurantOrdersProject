package com.example.restaurantul.domain;

import javafx.util.Pair;

import java.util.Objects;

public class OrderItem extends Entity<Integer>{
    private int orderId;
    private int menuItemId;

    public OrderItem(Integer integer, int orderId, int menuItemId) {
        super(integer);
        this.orderId = orderId;
        this.menuItemId = menuItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return orderId == orderItem.orderId && menuItemId == orderItem.menuItemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, menuItemId);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", menuItemId=" + menuItemId +
                '}';
    }
}
