package ru.djt.jmusic.entity;

import jakarta.persistence.*;

@Entity
@Table(name="chart")
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="music_id")
    private Music music;

    public Chart(Long id, String name, Music music) {
        this.id = id;
        this.name = name;
        this.music = music;
    }

    public Chart() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
