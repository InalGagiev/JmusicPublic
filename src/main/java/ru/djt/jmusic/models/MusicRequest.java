package ru.djt.jmusic.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MusicRequest {
    private String title_ru;
    private String title_en;
    private String path;
    private String artist;
    private String likes;
    private String dislikes;
}
