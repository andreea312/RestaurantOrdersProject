package com.example.restaurantul.repository.DBRepository;

import com.example.restaurantul.domain.MenuItem;
import com.example.restaurantul.domain.Table;
import com.example.restaurantul.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDBRepository implements Repository<Integer, MenuItem> {
    private String url;
    private String username;
    private String password;

    public MenuItemDBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public MenuItem find(Integer id) {
        String sql = "SELECT * FROM menuitems WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int idd = rs.getInt("id");
                String category = rs.getString("category");
                String item = rs.getString("item");
                float price = rs.getFloat("price");
                String currency = rs.getString("currency");
                return new MenuItem(idd, category, item, price, currency);
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<MenuItem> getAll() {
        List<MenuItem> menuitemss = new ArrayList<>();
        String sql = "SELECT * FROM \"menuitems\"";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int id = rs.getInt("id");
                String category = rs.getString("category");
                String item = rs.getString("item");
                float price = rs.getFloat("price");
                String currency = rs.getString("currency");

                MenuItem menuItem = new MenuItem(id,category,item,price,currency);
                menuitemss.add(menuItem);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return menuitemss;
    }

    @Override
    public void add(MenuItem menuItem){

    }
}

