package com.example.restaurantul.repository.DBRepository;

import com.example.restaurantul.domain.Order;
import com.example.restaurantul.domain.OrderItem;
import com.example.restaurantul.domain.OrderStatus;
import com.example.restaurantul.repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class OrdersDBRepository implements Repository<Integer, Order> {
    private String url;
    private String username;
    private String password;

    public OrdersDBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Order find(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Order> getAll() {
        List<Order> orderss = new ArrayList<>();
        String sql = "SELECT * FROM \"orders\"";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                int id = rs.getInt("id");
                int tableId = rs.getInt("tableid");
                LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
                OrderStatus status = OrderStatus.valueOf(rs.getString("status"));
                Order order = new Order(id, tableId, date, status);
                orderss.add(order);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return orderss;
    }

    public void add(Order order){
        String sql = "INSERT INTO orders(id, tableid, date, status) VALUES(?, ?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getTableId());
            statement.setTimestamp(3, Timestamp.valueOf(order.getDate()));
            statement.setString(4, order.getStatus().toString());
            statement.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
