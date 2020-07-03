package com.patand.Cinema.repository;

import com.patand.Cinema.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow,Long> {
    List<MovieShow> findAllByMovieId(Long id);
}
