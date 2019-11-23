package com.example.sportsbetting.repository;

import com.example.sportsbetting.domain.Bet;
import org.springframework.data.repository.CrudRepository;

public interface BetRepository extends CrudRepository<Bet, Integer> {
}
