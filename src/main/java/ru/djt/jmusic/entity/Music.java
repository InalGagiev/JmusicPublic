package ru.djt.jmusic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "Music")
@Table(name = "music")
public class Music {
    @Id
    @SequenceGenerator(name = "music_seq", sequenceName = "music_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "music_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "title_ru")
    private String titleRu;

    @Column(name = "title_en")
    private String name;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = true)
    private Album album;

    @Column(name = "likes")
    private String likes;

    @Column(name = "path")
    private String path;

    @Column(name = "dislikes")
    private String dislikes;

    @Column(name = "release_date")
    private LocalDate releaseDate;
}