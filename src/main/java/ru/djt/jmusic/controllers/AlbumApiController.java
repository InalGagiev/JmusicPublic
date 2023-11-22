package ru.djt.jmusic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.djt.jmusic.entity.Album;
import ru.djt.jmusic.entity.Artist;
import ru.djt.jmusic.models.AlbumCreateDto;
import ru.djt.jmusic.repo.AlbumRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/album")
public class AlbumApiController {
    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/{albumName}")
    public ResponseEntity<?> getAlbum(@PathVariable String albumName){
        Album album = albumRepository.findByName(albumName).orElseThrow(() -> new RuntimeException("ошибка в поиске альбома"));

        return albumRepository.findById(album.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createAlbum(@RequestBody AlbumCreateDto albumCreateDto){
        Album album = new Album();

        album.setName(albumCreateDto.getName());

        Album savedArtist = albumRepository.save(album);
        return ResponseEntity.ok("альбом успешно создан");
    }

    @PutMapping("/{albumName}")
    public ResponseEntity<?> updateAlbum(@PathVariable String albumName, @RequestBody Album album){
        Album albumOptional = albumRepository.findByName(albumName).orElseThrow(() -> new RuntimeException("ошибка в поиске альбома"));

        Album savedArtisst = albumRepository.save(album);
        return ResponseEntity.ok("данные о альбоме успешно обновлены");
    }

    @DeleteMapping("/{albumName}")
    public ResponseEntity<?> deleteAlbum(@PathVariable String albumName) {
        Album album = albumRepository.findByName(albumName).orElseThrow(() -> new RuntimeException("ошибка в поиске альбома"));

        if (!albumRepository.existsById(album.getId())) {
            return ResponseEntity.notFound().build();
        }
        albumRepository.deleteById(album.getId());
        return ResponseEntity.ok("пользователь успешно удален");
    }
}
