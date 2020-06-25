package com.patand.Cinema.service;

import com.patand.Cinema.model.Reservation;
import com.patand.Cinema.model.User;

import java.util.List;

public interface IReservationService {
    void save(Reservation reservation, User user);
    List<Reservation> showAllReservationWithStatusFalse();
    void confirmReservation(Long id);
    List<Reservation> showAllUserReservation(Long userId);
    void deleteReservation(Long id);
    Reservation findById(Long id);
    void update(Reservation reservation);
}
