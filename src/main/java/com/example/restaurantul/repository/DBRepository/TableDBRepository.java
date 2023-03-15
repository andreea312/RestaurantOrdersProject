package com.example.restaurantul.repository.DBRepository;

import com.example.restaurantul.domain.Table;
import com.example.restaurantul.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDBRepository implements Repository<Integer, Table> {
    private String url;
    private String username;
    private String password;

    public TableDBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Table find(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Table> getAll() {
        List<Table> tabless = new ArrayList<>();
        String sql = "SELECT * FROM \"tables\"";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int id = rs.getInt("id");
                Table table = new Table(id);
                tabless.add(table);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tabless;
    }

    @Override
    public void add(Table table){

    }
}
