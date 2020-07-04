package com.patand.Cinema.service;

import com.patand.Cinema.model.Room;

import java.util.List;

public interface IRoomService {
    void add(Room room);
    List<Room> findAll();
    Room getById(Long id);
}
