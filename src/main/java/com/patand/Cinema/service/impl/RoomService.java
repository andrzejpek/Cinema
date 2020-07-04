package com.patand.Cinema.service.impl;

import com.patand.Cinema.model.Movie;
import com.patand.Cinema.model.Room;
import com.patand.Cinema.repository.RoomRepository;
import com.patand.Cinema.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public void add(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room getById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }
}
