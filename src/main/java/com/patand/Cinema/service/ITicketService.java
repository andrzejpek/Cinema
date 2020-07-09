package com.patand.Cinema.service;

import com.patand.Cinema.model.Ticket;

import java.util.List;

public interface ITicketService {
    void add(Ticket ticket);
    List<Ticket> findAll();
    Ticket getById(Long id);
}
