package com.patand.Cinema.service;

import com.patand.Cinema.model.MovieShow;

import java.util.List;

public interface IMovieShowService {
    void add(MovieShow movieShow);
    MovieShow findById(Long id);
    List<MovieShow> findAll();
    List<MovieShow> findAllByMovieId(Long movieId);
}
