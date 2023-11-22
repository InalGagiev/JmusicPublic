package ru.djt.jmusic.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.djt.jmusic.entity.PlaylistSongEntity;

import java.util.Optional;

@Repository
public interface PlaylistSongRepository extends CrudRepository<PlaylistSongEntity, Long> {
    //ищет айди связи плейлиста и песни
    Optional<PlaylistSongEntity> findByPlaylistIdAndMusicId(Long playlistId, Long musicId);
}

