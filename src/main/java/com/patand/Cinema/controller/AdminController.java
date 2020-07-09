package com.patand.Cinema.controller;

import com.patand.Cinema.model.*;
import com.patand.Cinema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private IMovieShowService iMovieShowService;

    @Autowired
    private IReservationService iReservationService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private ITicketService iTicketService;

    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/main")
    public String showAdminPanel(Model model){
        List<Movie> categories = movieService.findAll();
        model.addAttribute("categories", categories);
        return "homePage";
    }

    @GetMapping(value = "/addTicket")
    public String showTicket(Model model){
        model.addAttribute("ticket",new Ticket());
        return "addTicket";
    }

    @GetMapping(value = "/addMovie")
    public String showAddMovieForm(Model model){
        model.addAttribute("movie", new Movie());
        return "addMovie";
    }

    @GetMapping(value = "/addMovieShow")
    public String showAddMovieShowForm(Model model){
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        List<Room> rooms = roomService.findAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("movieShowRequest", new MovieShowRequest());
        return "addMovieShow";
    }
    @GetMapping(value = "/addRoom")
    public String showAddRoom(Model model){
        model.addAttribute("room",new Room());
        return "addRoom";
    }

    @PostMapping(value = "/addRoom")
    public String addRoom(@ModelAttribute Room room){
        roomService.add(room);
        return "redirect:/admin/addRoom";
    }
    @PostMapping(value = "/addTicket")
    public String addTicket(@ModelAttribute Ticket ticket){
        iTicketService.add(ticket);
        return "redirect:/admin/addTicket";
    }


    @PostMapping(value = "/addMovie")
    public String addCategory(@ModelAttribute Movie movie){
        movieService.add(movie);
        return "redirect:/admin/main";
    }

    @PostMapping(value = "/addMovieShow")
    public String addMovieShow(@ModelAttribute MovieShowRequest movieShowRequest){
        MovieShow movieShow = new MovieShow();
        movieShow.setDateTime(movieShowRequest.getShowDateTime());
        movieShow.setMovie(movieService.getById(Long.valueOf(movieShowRequest.getMovieId())));
        movieShow.setRoom(roomService.getById(Long.valueOf(movieShowRequest.getRoomId())));
        iMovieShowService.add(movieShow);
        return "redirect:/admin/main";
    }

    @GetMapping(value = "/reservation")
    public String showReservation(Model model){
        model.addAttribute("reservations", iReservationService.showAllReservationWithStatusFalse());
        return "reservationListForm";
    }




}
