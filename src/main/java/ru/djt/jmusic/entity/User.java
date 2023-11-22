package ru.djt.jmusic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false, length = 60)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Playlist> playlists;

    @ManyToOne
    @JoinColumn(name="music_id", nullable = true) // имя колонки-внешнего ключа
    private Music music;

    @ManyToOne
    @JoinColumn(name="artist_id", nullable = true) // имя колонки-внешнего ключа
    private Artist artist;

    @ManyToOne
    @JoinColumn(name="album_id", nullable = true) // имя колонки-внешнего ключа
    private Album album;

    public User() {

    }
}