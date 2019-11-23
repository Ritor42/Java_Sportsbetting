package com.example.sportsbetting.repository;

import com.example.sportsbetting.domain.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Optional<Player> findByEmailAndPassword(String name, String password);
}
