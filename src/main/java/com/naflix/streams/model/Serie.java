package com.naflix.streams.model;

import com.naflix.streams.service.ConsumeMyMemory;
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

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episode> episodes = new ArrayList<>();

    public Serie(DataSerie serie) {
        this.title = serie.title();
        this.totalSeason = serie.totalSeasons();
        this.rating = serie.rating();
        this.category = Category.fromString(serie.category().split(",")[0].trim());
        this.actors = serie.actors();
        this.plot = ConsumeMyMemory.getTranslate(serie.plot()).trim();
        this.poster = serie.poster();
    }

    public void setEpisodios(List<Episode> episodes){
        episodes.forEach(e -> e.setSerie(this));
        this.episodes = episodes;
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
