package com.example.restaurantul.domain;

public class Table extends Entity<Integer>{
    public Table(int id){
        super(id);
    }
    @Override
    public String toString() {
        return "Table" + getId();
    }
}