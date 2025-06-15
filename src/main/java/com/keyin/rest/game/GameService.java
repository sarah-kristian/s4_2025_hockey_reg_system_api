package com.keyin.rest.game;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        return gameOptional.orElse(null);
    }

    public List<Game> getGameByTeamName(String name) {
        return gameRepository.findByHomeTeam_NameOrAwayTeam_Name(name, name);
    }

    public List<Game> getGameByLocation(String location) {
        return gameRepository.findByLocation(location);
    }

    public List<Game> getGamesByTeamAndLocation(String name, String location) {
        List<Game> byTeam = gameRepository.findByHomeTeam_NameOrAwayTeam_Name(name, name);
        return byTeam.stream()
                .filter(game -> location.equals(game.getLocation()))
                .toList();
    }

    public Game createGame(Game newGame) {
        return gameRepository.save(newGame);
    }

    public Game updateGame(long id, Game updatedGame) {
        Optional<Game> gameToUpdateOptional = gameRepository.findById(id);

        if (gameToUpdateOptional.isPresent()) {
            Game gameToUpdate = gameToUpdateOptional.get();

            gameToUpdate.setHomeTeam(updatedGame.getHomeTeam());
            gameToUpdate.setAwayTeam(updatedGame.getAwayTeam());
            gameToUpdate.setLocation(updatedGame.getLocation());
            gameToUpdate.setScheduledDate(updatedGame.getScheduledDate());
            gameToUpdate.setHomeScore(updatedGame.getHomeScore());
            gameToUpdate.setAwayScore(updatedGame.getAwayScore());

            return gameRepository.save(gameToUpdate);
        }

        return null;
    }

    public void deleteGameById(long id) {
        gameRepository.deleteById(id);
    }

}
