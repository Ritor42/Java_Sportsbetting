package com.example.sportsbetting.repository;

import com.example.sportsbetting.domain.SportEvent;
import org.springframework.data.repository.CrudRepository;

public interface SportEventRepository extends CrudRepository<SportEvent, Integer> {
}