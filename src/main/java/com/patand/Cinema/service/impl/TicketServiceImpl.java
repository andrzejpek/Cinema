package com.patand.Cinema.service.impl;

import com.patand.Cinema.model.Ticket;
import com.patand.Cinema.repository.TicketRepository;
import com.patand.Cinema.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public void add(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }
}
