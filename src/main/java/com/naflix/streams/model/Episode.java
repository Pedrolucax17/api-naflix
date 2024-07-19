package com.naflix.streams.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tbl_episode")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberSeason;
    private String title;
    private LocalDate released;
    private String episode;
    private Double imdbRating;

    public Episode(Integer numberSeason, DataEpisode dataEpisode){
        this.numberSeason = numberSeason;
        this.title = dataEpisode.title();
        try{
            this.released = LocalDate.parse(dataEpisode.released());
        }catch (DateTimeParseException e){
            released = null;
        }
        this.episode = dataEpisode.episode();
        try{
            this.imdbRating = Double.parseDouble(dataEpisode.imdbRating());
        }catch(NumberFormatException e){
            imdbRating = 0.0;
        }

    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", numberSeason=" + numberSeason +
                ", title='" + title + '\'' +
                ", released=" + released +
                ", episode='" + episode + '\'' +
                ", imdbRating=" + imdbRating +
                '}';
    }
}
