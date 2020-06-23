package com.patand.Cinema.repository;

import com.patand.Cinema.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieShowRepository extends JpaRepository<MovieShow,Long> {
    List<MovieShow> findAllByCategoryId(Long id);
}
