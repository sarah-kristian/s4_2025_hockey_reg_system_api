package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findAll();
    List<Game> findByHomeTeam_NameOrAwayTeam_Name(String homeName, String awayName);
    public List<Game> findByLocation(String location);
    public List<Game> findByScheduledDateBetween(Calendar start, Calendar end);
    public List<Game> findByScheduledDate(Calendar scheduledDate);
}
