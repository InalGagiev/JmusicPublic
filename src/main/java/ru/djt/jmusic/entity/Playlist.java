package ru.djt.jmusic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name="playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    //эта аннотация фиксит ошибку с бесконечным зыцикливанием ссылки
    //User -> Playlists -> Playlist -> User -> Playlists -> Playlist
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id") // "user_id" - это имя колонки в таблице Playlist, которая является внешним ключом к User
    private User user;


    public Playlist(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public Playlist() {

    }
}
