package ru.djt.jmusic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private List<Music> music;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist;

    public Album(Long id, String name, Artist artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }

    public Album() {

    }
}
