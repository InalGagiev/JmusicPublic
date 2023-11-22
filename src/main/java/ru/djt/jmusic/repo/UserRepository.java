package ru.djt.jmusic.repo;

import org.springframework.data.repository.CrudRepository;
import ru.djt.jmusic.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

}