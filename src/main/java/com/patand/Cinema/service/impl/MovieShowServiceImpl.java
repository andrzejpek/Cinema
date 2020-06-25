package com.patand.Cinema.service.impl;

import com.patand.Cinema.model.MovieShow;
import com.patand.Cinema.repository.MovieShowRepository;
import com.patand.Cinema.service.IMovieShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieShowServiceImpl implements IMovieShowService {

    @Autowired
    MovieShowRepository movieShowRepository;

    @Override
    public void add(MovieShow movieShow) {
        movieShowRepository.save(movieShow);
    }

    @Override
    public MovieShow findById(Long id) {
        return movieShowRepository.findById(id).orElse(null);
    }

    @Override
    public List<MovieShow> findAll() {
        return movieShowRepository.findAll();
    }


    @Override
    public List<MovieShow> findAllByMovieId(Long movieId) {
        return movieShowRepository.findAllByMovieId(movieId);
    }
}