package ru.djt.jmusic.models;
import lombok.Data;

@Data
public class PlaylistSongRequest {
    private String playlist;
    private String music;
}
