package ru.djt.jmusic.repo;

import org.springframework.data.repository.CrudRepository;
import ru.djt.jmusic.entity.Artist;
import ru.djt.jmusic.entity.Music;

import java.util.Optional;

public interface ArtistRepository extends CrudRepository<Artist, Long>{
    Optional<Artist> findByName(String name);
}
