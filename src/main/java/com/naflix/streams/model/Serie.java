package com.naflix.streams.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tbl_serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "total_season")
    private Integer totalSeason;

    private String rating;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String actors;

    private String plot;

    private String poster;

    @Transient
    private List<Episode> episodes = new ArrayList<>();

    public Serie(DataSerie serie) {
        this.title = serie.title();
        this.totalSeason = serie.totalSeasons();
        this.rating = serie.rating();
        this.category = Category.fromString(serie.category().split(",")[0].trim());
        this.actors = serie.actors();
        this.plot = serie.plot();
        this.poster = serie.poster();
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", totalSeason=" + totalSeason +
                ", rating='" + rating + '\'' +
                ", category='" + category + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
