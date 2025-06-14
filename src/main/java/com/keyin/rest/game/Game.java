package com.keyin.rest.game;
import com.keyin.rest.team.Team;
//import jakarta.persistence.*;
import jakarta.persistence.*;

import java.util.Calendar;

@Entity
public class Game {
    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "game_sequence")
    private long id;

    @ManyToOne
    private Team homeTeam;

    @ManyToOne
    private Team awayTeam;
    private Calendar scheduledDate;
    private String location;
    private int homeScore; //nullable
    private boolean completed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Calendar scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {return awayTeam; }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
