package ru.djt.jmusic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.djt.jmusic.entity.Music;
import ru.djt.jmusic.entity.Playlist;

import java.util.Optional;
 
@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findByName(String titleEn);
}