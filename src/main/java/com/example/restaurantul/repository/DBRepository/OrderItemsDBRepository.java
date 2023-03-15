package com.example.restaurantul.repository.DBRepository;
import com.example.restaurantul.domain.Order;
import com.example.restaurantul.domain.OrderItem;
import com.example.restaurantul.repository.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsDBRepository implements Repository<Integer, OrderItem> {
    private String url;
    private String username;
    private String password;

    public OrderItemsDBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public OrderItem find(Integer integer) {
        return null;
    }

    @Override
    public Iterable<OrderItem> getAll() {
        List<OrderItem> orderitemss = new ArrayList<>();
        String sql = "SELECT * FROM \"orderitems\"";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int id = rs.getInt("id");
                int orderId = rs.getInt("orderid");
                int menuItemId = rs.getInt("menuitemid");
                OrderItem orderItem = new OrderItem(id, orderId, menuItemId);
                orderitemss.add(orderItem);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return orderitemss;
    }

    public void add(OrderItem orderItem){
        String sql = "INSERT INTO orderitems(id, orderid, menuitemid) VALUES(?, ?, ?)";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderItem.getId());
            statement.setInt(2, orderItem.getOrderId());
            statement.setInt(3, orderItem.getMenuItemId());
            statement.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
