package com.patand.Cinema.service.impl;

import com.patand.Cinema.model.Movie;
import com.patand.Cinema.repository.MovieRepository;
import com.patand.Cinema.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MovieServiceImpl implements IMovieService
{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void add(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
