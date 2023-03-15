package com.example.restaurantul.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order extends Entity<Integer>{
    private int tableId;
    private LocalDateTime date;
    private OrderStatus status;


    public Order(Integer id, int idTable, LocalDateTime date, OrderStatus status) {
        super(id);
        this.tableId = idTable;
        this.date = date;
        this.status = status;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return tableId == order.tableId && date.equals(order.date) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tableId,date, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idTable=" + tableId +
                ", idsMenuItems=" +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
