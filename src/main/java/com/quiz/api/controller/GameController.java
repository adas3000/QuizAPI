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

    @GetMapping("/all_connected/{uuid}")
    public ResponseEntity<Object> allPlayersConnected(@PathVariable("uuid")String uuid){
        return gameService.allPlayersConnected(uuid);
    }

    @DeleteMapping("/{uuid}/{serial}")
    public ResponseEntity<Object> getOutFromGame(@PathVariable("uuid")String uuid,@PathVariable("serial")String serial){
        return gameService.removePlayerFromGame(uuid,serial);
    }

    @PutMapping("/update/{uuid}/{serial}/{howmany}")
    public ResponseEntity<Object> updateDeviceScoreInGame(@PathVariable("uuid")String uuid,@PathVariable("serial")String serial,
                                                          @PathVariable("howmany")String howmany){
        return gameService.updateDeviceScoreInGame(uuid, serial, howmany);
    }

    @GetMapping("/find/score/{uuid}")
    public ResponseEntity<Object> findScoresByUUID(@PathVariable("uuid")String uuid){
        return null;
    }

    @DeleteMapping("/all")
    public ResponseEntity<Object> dropAll(){
        return gameService.removeAllGames();
    }

    @GetMapping("/all")
    public ResponseEntity<Object> findAll(){
        return gameService.findAll();
    }



}

