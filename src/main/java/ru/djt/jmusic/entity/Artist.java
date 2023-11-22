package ru.djt.jmusic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="albom_id")
    private Album album;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Music> music;

    private String biography;

    public Artist(Long id, String name, Album album, String biography) {
        this.id = id;
        this.name = name;
        this.album = album;
        this.biography = biography;
    }

    public Artist() {

    }
}
