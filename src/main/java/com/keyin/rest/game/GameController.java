package com.keyin.rest.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/game")

public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/search")
    public List<Game> searchGames(
            @RequestParam(value = "team_name", required = false) String teamName,
            @RequestParam(value = "location", required = false) String location
    ) {
        if (teamName != null && location != null) {
            return gameService.getGamesByTeamAndLocation(teamName, location);
        } else if (teamName != null) {
            return gameService.getGameByTeamName(teamName);
        } else if (location != null) {
            return gameService.getGameByLocation(location);
        } else {
            return gameService.getAllGames(); // optional fallback
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable long id) {
        Game gameToReturn = gameService.getGameById(id);

        if (gameToReturn == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(gameToReturn);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game newGame = gameService.createGame(game);

        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable long id, @RequestBody Game game) {
        Game updatedGame = gameService.updateGame(id, game);
        if (updatedGame == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameById(@PathVariable long id) {
        gameService.deleteGameById(id);
        return ResponseEntity.noContent().build();
    }
}
