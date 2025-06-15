package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findAll();
    List<Game> findByHomeTeam_NameOrAwayTeam_Name(String homeName, String awayName);
    List<Game> findByLocation(String location);
}
