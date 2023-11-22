package ru.djt.jmusic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name="playlist-song")
public class PlaylistSongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Playlist playlist;

    @ManyToOne
    private Music music;
}
