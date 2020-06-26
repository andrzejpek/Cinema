package com.patand.Cinema.controller;


import com.patand.Cinema.model.MovieShow;
import com.patand.Cinema.model.Reservation;
import com.patand.Cinema.model.User;
import com.patand.Cinema.service.IMovieShowService;
import com.patand.Cinema.service.IReservationService;
import com.patand.Cinema.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMovieShowService movieShowService;

    @Autowired
    private IReservationService reservationService;

    @GetMapping(value = "/addReservation")
    public String showReservationForm( Model model){
        List<MovieShow> categories = movieShowService.findAll();
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("movieList", categories);
        return "reservation/reservationForm";
    }

    @PostMapping(value = "/addReservation")
    public String addReservation(@ModelAttribute Reservation reservation, Principal principal){
        User user = getUser(principal);
        reservationService.save(reservation, user);
        return "redirect:/";
    }


    @GetMapping(value = "/userReservation")
    public String showUserReservationsForm(Principal principal, Model model){
        User user = getUser(principal);
        List<Reservation> reservations = reservationService.showAllUserReservation(user.getId());
        model.addAttribute("userReservations", reservations);
        return "reservation/userReservationForm";
    }

    @GetMapping(value = "/userReservation/{reservationId}")
    public String rejectReservation(@PathVariable("reservationId") Long reservationId){
        reservationService.deleteReservation(reservationId);
        return "redirect:/reservation/userReservation";
    }

    @GetMapping(value = "/editReservation/{id}")
    public String showEditReservationForm(@PathVariable("id") Long reservationId, Model model){
        Reservation reservation = reservationService.findById(reservationId);
        List<MovieShow> categories = movieShowService.findAll();
        model.addAttribute("movieList", categories);
        model.addAttribute("editReservation", reservation);
        return "reservation/editReservationForm";
    }

    @PostMapping(value = "/editReservation/{id}")
    public String editReservation(@ModelAttribute Reservation reservation){
        reservationService.update(reservation);
        return "redirect:/reservation/userReservation";
    }


    private User getUser(Principal principal) {
        return userService.findById(Long.parseLong(userService.findByEmail(principal.getName()).getId().toString()));
    }
}
