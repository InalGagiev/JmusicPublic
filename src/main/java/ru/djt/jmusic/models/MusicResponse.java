package ru.djt.jmusic.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MusicResponse {
    private String release_date;
    private String likes;
    private String dislikes;
    private String album;
    private String name;
    private String path;

    public MusicResponse() {

    }
}