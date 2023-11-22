package ru.djt.jmusic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.djt.jmusic.entity.Album;
import ru.djt.jmusic.entity.Artist;
import ru.djt.jmusic.entity.Music;
import ru.djt.jmusic.models.MusicCreate;
import ru.djt.jmusic.models.MusicResponse;
import ru.djt.jmusic.repo.AlbumRepository;
import ru.djt.jmusic.repo.ArtistRepository;
import ru.djt.jmusic.repo.MusicRepository;

import java.util.List;

@RestController
@RequestMapping("/api/music")
public class MusicApiController {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private final MusicRepository musicRepository;

    @Autowired
    public MusicApiController(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @GetMapping("/get-music/{musicname}")      // Is need a "?" ?
    public ResponseEntity<?> getMusicById(@PathVariable String musicname) {
        MusicResponse musicResponse = new MusicResponse();

        Music music  = musicRepository.findByName(musicname).orElseThrow(() -> new RuntimeException("music not found"));

        //music.getArtist().getId() тут мы берем из первичного ключа айди
        artistRepository.findById(music.getArtist().getId()).orElseThrow(() -> new RuntimeException("artist not found"));

        musicResponse.setName(music.getName());
        musicResponse.setPath(music.getPath());
        musicResponse.setLikes(music.getLikes());
        musicResponse.setDislikes(music.getDislikes());

        return ResponseEntity.ok(musicResponse);
    }

    @PostMapping("/create-music")
    public ResponseEntity<Music> createMusic(@RequestBody MusicCreate musicCreate) {
        Artist artistId = artistRepository.findByName(musicCreate.getArtist()).orElseThrow(() -> new RuntimeException("ошибка в поиске артиста  " + musicCreate.getArtist()));
        Album albumId = albumRepository.findByName(musicCreate.getAlbum()).orElseThrow(() -> new RuntimeException("ошибка в поиске альбома"));

        Music musicSave = new Music();

        musicSave.setName(musicCreate.getName());
        musicSave.setPath(musicCreate.getPath());
        musicSave.setAlbum(albumId);
        musicSave.setArtist(artistId);

        musicRepository.save(musicSave);
        return ResponseEntity.ok(musicSave);
    }

    @PutMapping("/update-music/{musicName}")
    public ResponseEntity<?> updateMusic(@RequestBody MusicCreate musicCreate, @PathVariable String musicName) {
        Music music = musicRepository.findByName(musicName).orElseThrow(() -> new RuntimeException("ошибка в поиске песни"));

        if (!musicRepository.existsById(music.getId())) {
            return ResponseEntity.notFound().build();
        }
        Artist artistId = artistRepository.findByName(musicCreate.getArtist()).orElseThrow(() -> new RuntimeException("ошибка в поиске артиста  " + musicCreate.getArtist()));
        Album albumId = albumRepository.findByName(musicCreate.getAlbum()).orElseThrow(() -> new RuntimeException("ошибка в поиске альбома"));

        music.setName(musicCreate.getName());
        music.setPath(musicCreate.getPath());
        music.setAlbum(albumId);
        music.setArtist(artistId);

        musicRepository.save(music);
        return ResponseEntity.ok("песня успешно обновлена");
    }
    @DeleteMapping("/music-delete/{username}")
    public ResponseEntity<?> deleteMusic(@PathVariable String username) {
        Music music = musicRepository.findByName(username).orElseThrow(() -> new RuntimeException("ошибка в поиске песни"));

        if (!musicRepository.existsById(music.getId())) {
            return ResponseEntity.notFound().build();
        }
        musicRepository.deleteById(music.getId());
        return ResponseEntity.ok("песня успешно удалена");
    }

    @GetMapping
    public ResponseEntity<List<Music>> getMusics() {
        return ResponseEntity.ok(musicRepository.findAll());
    }
}