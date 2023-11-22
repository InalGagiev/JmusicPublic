package ru.djt.jmusic.repo;

import org.springframework.data.repository.CrudRepository;
import ru.djt.jmusic.entity.Album;
import ru.djt.jmusic.entity.Artist;

import java.util.Optional;

public interface AlbumRepository extends CrudRepository<Album, Long>{
    Optional<Album> findByName(String name);
}
