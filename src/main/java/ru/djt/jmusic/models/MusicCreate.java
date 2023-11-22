package ru.djt.jmusic.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MusicCreate {
    private String path;
    private String name;
    private String artist;
    private String album;
}