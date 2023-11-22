package ru.djt.jmusic.controllers;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.djt.jmusic.entity.Music;
import ru.djt.jmusic.entity.Playlist;
import ru.djt.jmusic.entity.PlaylistSongEntity;
import ru.djt.jmusic.entity.User;
import ru.djt.jmusic.models.PlaylistSongRequest;
import ru.djt.jmusic.repo.MusicRepository;
import ru.djt.jmusic.repo.PlaylistRepository;
import ru.djt.jmusic.models.PlaylistCreateDto;
import ru.djt.jmusic.repo.PlaylistSongRepository;
import ru.djt.jmusic.repo.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistApiController {
    @Autowired
    private PlaylistRepository playListRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create-playlist")
    public ResponseEntity<?> CreatePlaylist(@RequestBody PlaylistCreateDto PlaylistCreateDto){
        Playlist playlist = new Playlist();
        playlist.setName(PlaylistCreateDto.getName());

        Playlist savedPlaylist = playListRepository.save(playlist);
        return ResponseEntity.ok("плэй лист успешно создан");
    }

    @PutMapping("/update-playlist/{username}")
    public ResponseEntity<?> updatePlaylist(@PathVariable String username, @RequestBody PlaylistCreateDto PlaylistCreateDto){
        Playlist playlistOptional = playListRepository.findByName(username).orElseThrow(() -> new RuntimeException("ошибка в поиске плейлиста"));

        playlistOptional.setName(PlaylistCreateDto.getName());

        Playlist savedPlaylist = playListRepository.save(playlistOptional);
        return ResponseEntity.ok("успешно обновленны данные о плейлисте");
    }

    @DeleteMapping("/playlist-delete/{playlist}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable String playlist) {
        Playlist playlistDelete = playlistRepository.findByName(playlist).orElseThrow(() -> new RuntimeException("плей лист не найден"));

        if (!playListRepository.existsById(playlistDelete.getId())) {
            return ResponseEntity.notFound().build();
        }

        playListRepository.deleteById(playlistDelete.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/playlist-music-add")
    public ResponseEntity<?> palylistMusicAdd(@RequestBody PlaylistSongRequest playlistSongRequest){
        String playlist = playlistSongRequest.getPlaylist();
        String music = playlistSongRequest.getMusic();

        Playlist playlistIdEntity = playListRepository.findByName(playlist).orElseThrow(() -> new RuntimeException("Playlist not found"));
        Music musicIdEntity = musicRepository.findByName(music).orElseThrow(() -> new RuntimeException("Playlist not found"));

        /*
        тут мы передаем именно сущности т.к. в полях с первичным ключом
        мы сохряняем именно сами сущности а не конкретно их айди, айди
        в последствии с помощью @ManyToOne сам с таблице сохрянет
        именно первичный ключ, наша же задача передать весь объект
        сущности
         */
        PlaylistSongEntity playlistSong = new PlaylistSongEntity();
        playlistSong.setPlaylist(playlistIdEntity);
        playlistSong.setMusic(musicIdEntity);

        playlistSong = playlistSongRepository.save(playlistSong);

        return ResponseEntity.ok("Айди плейлиста: " + playlistIdEntity.getId() + ", Айди песни: " + musicIdEntity.getId());
    }

    @DeleteMapping("/playlist-music-delete")
    public ResponseEntity<?> playlist_music_delete(@RequestBody PlaylistSongRequest playlistSongRequest){
        String playlist = playlistSongRequest.getPlaylist();
        String music = playlistSongRequest.getMusic();

        //тут мы получаем именно те записи по которым было найдено имя
        Playlist playlistIdEntity = playListRepository.findByName(playlist).orElseThrow(() -> new RuntimeException("Playlist not found"));
        Music musicIdEntity = musicRepository.findByName(music).orElseThrow(() -> new RuntimeException("Playlist not found"));

        //ищем сущность по двум полям
        Optional<PlaylistSongEntity> playlistsong = playlistSongRepository.findByPlaylistIdAndMusicId(playlistIdEntity.getId(), musicIdEntity.getId());

        //преобразовываем ее в Long для дальнейшего удаления
        Long playlistSongId = playlistsong.map(PlaylistSongEntity::getId).orElse(null);

        playlistSongRepository.deleteById(playlistSongId);

        return ResponseEntity.ok("песня успешно удалена из плейлиста");
    }

    @Autowired
    private PlaylistRepository playlistRepository; // Предполагается, что у вас есть репозиторий для работы с плейлистами

    @GetMapping("/users/playlists")
    public ResponseEntity<?> getAllPlaylistsForUser(HttpServletRequest request) {
        String secretKey = "secret";

        String token = request.getHeader("Authorization");

        String cleanedToken = token.substring(7);

        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(cleanedToken);

        String username = (String) claims.getBody().get("sub");

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found;"));

        if(user == null) {
            return null;
        }

        return ResponseEntity.ok(user.getPlaylists());
    }
}