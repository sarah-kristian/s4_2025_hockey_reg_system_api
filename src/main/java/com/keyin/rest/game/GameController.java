package com.keyin.rest.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/game")

public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/search")
    public List<Game> getGameByTeamName(@RequestParam(value = "team_name", required = false) String teamName) {
        List<Game> results = new ArrayList<Game>();

        if (teamName != null) {
            results =  gameService.getGameByTeamName(teamName);
        }

        return results;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable long id) {
        Game gameToReturn = gameService.getGameById(id);

        if (gameToReturn == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(gameToReturn);
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        System.out.println(game.getLocation());
        System.out.println(game.getScheduledDate());
        System.out.println(game.getHomeTeam().getName());
        System.out.println(game.getAwayTeam().getName());
        System.out.println(game.getHomeScore());
        System.out.println(game.isCompleted());

        return gameService.createGame(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable long id, @RequestBody Game game) {
        return ResponseEntity.ok(gameService.updateGame(id, game));
    }

    @DeleteMapping("/{id}")
    public void deleteGameById(@PathVariable long id) {
        gameService.deleteGameById(id);
    }
}
