package com.patand.Cinema.service.impl;


import com.patand.Cinema.model.Reservation;
import com.patand.Cinema.model.User;
import com.patand.Cinema.repository.ReservationRepository;
import com.patand.Cinema.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;



    @Override
    public void save(Reservation reservation, User user) {
        reservation.setStatus(false);
        reservation.setUser(user);
        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> showAllUserReservation(Long userId) {
        return reservationRepository.findAllByStatusTrueAndUserId(userId);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.findById(id).ifPresent(reservation -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
            reservationRepository.delete(reservation);
        });
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }
//    @Override
//    public void update(Reservation reservation) {
//        reservationRepository.findById(reservation.getId()).ifPresent(reservation1 -> {
//            reservation1.(reservation);
//            reservation1.setDateTime(reservation.getDateTime());
//            reservation1.setStatus(false);
//            reservationRepository.save(reservation1);
//        });
//    }

    @Override
    public List<Reservation> showAllReservationWithStatusFalse() {
        return reservationRepository.findAllByStatusFalse();
    }

    @Override
    public void confirmReservation(Long id) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
        reservationRepository.findById(id).ifPresent(r -> {
            r.setStatus(true);
            reservationRepository.save(r);
        });
    }




}
