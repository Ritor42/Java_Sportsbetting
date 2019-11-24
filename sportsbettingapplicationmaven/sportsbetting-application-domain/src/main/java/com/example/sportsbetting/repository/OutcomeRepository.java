package com.example.sportsbetting.repository;

import com.example.sportsbetting.domain.Outcome;
import org.springframework.data.repository.CrudRepository;

public interface OutcomeRepository extends CrudRepository<Outcome, Integer> {
}
