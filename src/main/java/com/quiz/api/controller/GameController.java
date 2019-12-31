package com.quiz.api.controller;

import com.quiz.api.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin
public class GameController {

    @Autowired
    private GameService gameService;


    @GetMapping("/{uuid}")
    public ResponseEntity<Object> findGameByUUID(@PathVariable("uuid")String uuid){
        return gameService.findGameByUUID(uuid);
    }

}

