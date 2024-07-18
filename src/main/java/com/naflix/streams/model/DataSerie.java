package com.naflix.streams.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(
        @JsonAlias("Title")
        String title,
        @JsonAlias("totalSeasons")
        Integer totalSeasons,
        @JsonAlias("imdbRating")
        String rating,
        @JsonAlias("Genre")
        String category,
        @JsonAlias("Actors")
        String actors,
        @JsonAlias("Plot")
        String plot,
        @JsonAlias("Poster")
        String poster
) {
}
