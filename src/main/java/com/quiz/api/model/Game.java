package com.quiz.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private Set<Device> players = new HashSet<>();

    @ManyToMany
    private List<Question> questions = new ArrayList<>();

    private int playersCount;

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    private String gameUUID =null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Device> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Device> players) {
        this.players = players;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getGameUUID() {
        return gameUUID;
    }

    public void setGameUUID(String gameUUID) {
        this.gameUUID = gameUUID;
    }
}
