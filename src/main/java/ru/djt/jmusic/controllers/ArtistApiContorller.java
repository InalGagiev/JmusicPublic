package ru.djt.jmusic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.djt.jmusic.entity.Artist;
import ru.djt.jmusic.entity.Playlist;
import ru.djt.jmusic.models.ArtistCreateDto;
import ru.djt.jmusic.repo.ArtistRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/artist")
public class ArtistApiContorller {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtists(@PathVariable Long id){
        return artistRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createArtist(@RequestBody ArtistCreateDto artistCreateDto){
        Artist artist = new Artist();

        artist.setName(artistCreateDto.getName());
        artist.setBiography(artistCreateDto.getBiography());

        Artist savedArtist = artistRepository.save(artist);
        return ResponseEntity.ok("исполнитель успешно создан");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable Long id, @RequestBody Artist artist){
        Optional<Artist> artistOptional = artistRepository.findById(id);

        if(!artistOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        artist.setId(id);

        Artist savedArtisst = artistRepository.save(artist);
        return ResponseEntity.ok("данные о исполнителе успешно обновлены");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        if (!artistRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        artistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
