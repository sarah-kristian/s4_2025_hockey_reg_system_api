package com.keyin.rest.game;
import com.keyin.rest.team.Team;
import jakarta.persistence.*;

import java.util.Calendar;

/**
 * Represents a scheduled or completed hockey game between two teams.
 * A game is considered completed if both home and away scores are set.
 * The match display format includes team names and scores.
 */

@Entity
public class Game {

    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence", allocationSize = 1)
    @GeneratedValue(generator = "game_sequence")
    private long id;

    @ManyToOne
    private Team homeTeam;

    @ManyToOne
    private Team awayTeam;
    private Calendar scheduledDate;
    private String location;
    private Integer homeScore;
    private Integer awayScore;


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

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {this.awayScore = awayScore;}

    public String getScoreDisplay() {
        if (homeScore == null || awayScore == null) {
            return "-- : --";
        }
        return homeScore + " : " + awayScore;
    }

    public String getMatchDisplay() {
        String homeName = homeTeam != null ? homeTeam.getName() : "TBD";
        String awayName = awayTeam != null ? awayTeam.getName() : "TBD";
        String score = getScoreDisplay();

        return homeName + " " + score + " " + awayName;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", match='" + getMatchDisplay() + '\'' +
                ", scheduledDate=" + (scheduledDate != null ? scheduledDate.getTime() : "TBD") +
                ", location='" + location + '\'' +
                '}';
    }
}
