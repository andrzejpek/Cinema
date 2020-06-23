package com.patand.Cinema.service;

import com.patand.Cinema.model.Movie;

import java.util.List;

public interface IMovieService {
    void add(Movie movie);
    List<Movie> findAll();
    Movie getById(Long id);
}
