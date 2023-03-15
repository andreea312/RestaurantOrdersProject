package com.example.restaurantul.repository;

import com.example.restaurantul.domain.Entity;

public interface Repository<ID, E extends Entity<ID>>{
    E find(ID id);
    Iterable<E> getAll();
    void add(E entity);
}
