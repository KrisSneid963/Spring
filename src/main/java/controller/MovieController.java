package com.example.demo.controller;

import com.example.demo.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final List<Movie> movieList = new ArrayList<>();

    public MovieController() {
        movieList.add(new Movie(1, "Shrek", "Andrew Adamson"));
        movieList.add(new Movie(2, "Avatar", "James Cameron"));
        movieList.add(new Movie(3, "Inception", "Christopher Nolan"));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        Optional<Movie> movie = movieList.stream()
                .filter(m -> m.getId() == id)
                .findFirst();

        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        movie.setId(movieList.size() + 1);
        movieList.add(movie);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String title) {
        List<Movie> result = movieList.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
