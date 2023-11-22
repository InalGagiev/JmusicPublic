package ru.djt.jmusic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.djt.jmusic.entity.Playlist;
import ru.djt.jmusic.entity.User;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
    //имя в сигнатуре это колонка по которой будет происходить поиск
    Optional<Playlist> findByName(String name);
}