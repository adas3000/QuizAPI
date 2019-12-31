package com.quiz.api.service;

import com.quiz.api.model.Game;
import com.quiz.api.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public ResponseEntity<Object> findGameByUUID(String uuid){

        Game game = gameRepository.findByGameUUID(uuid);

        if(game==null){
            return new ResponseEntity<>("no_such_game", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(game.getQuestions(),HttpStatus.OK);
    }

}
