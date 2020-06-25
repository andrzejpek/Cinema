package com.patand.Cinema.controller;

import com.patand.Cinema.model.Movie;
import com.patand.Cinema.model.MovieShow;
import com.patand.Cinema.service.IMovieService;
import com.patand.Cinema.service.IMovieShowService;
import com.patand.Cinema.service.IReservationService;
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

    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/main")
    public String showAdminPanel(Model model){
        List<Movie> categories = movieService.findAll();
        model.addAttribute("categories", categories);
        return "admin/homePage";
    }

    @GetMapping(value = "/addMovie")
    public String showAddCategoryForm(Model model){
        model.addAttribute("category", new Movie());
        return "admin/addMovie";
    }

    @GetMapping(value = "/addMovieShow")
    public String showAddSubcategoryForm(Model model){
        List<Movie> categories = movieService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("subcategory", new MovieShow());
        return "addMovieShow";
    }

    @PostMapping(value = "/addMovie")
    public String addCategory(@ModelAttribute Movie movie){
        movieService.add(movie);
        return "redirect:/admin/main";
    }

    @PostMapping(value = "/addMovieShow")
    public String addSubCategory(@ModelAttribute MovieShow movieShow){
        iMovieShowService.add(movieShow);
        return "redirect:/admin/main";
    }

    @GetMapping(value = "/reservation")
    public String showReservation(Model model){
        model.addAttribute("reservations", iReservationService.showAllReservationWithStatusFalse());
        return "admin/reservationListForm";
    }




}
