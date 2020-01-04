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
    public ResponseEntity<Object> findGameByUUID(@PathVariable("uuid") String uuid) {
        return gameService.findGameByUUID(uuid);
    }

    @GetMapping("/all_connected/{uuid}")
    public ResponseEntity<Object> allPlayersConnected(@PathVariable("uuid") String uuid) {
        return gameService.allPlayersConnected(uuid);
    }

    @DeleteMapping("/{uuid}/{serial}")
    public ResponseEntity<Object> getOutFromGame(@PathVariable("uuid") String uuid, @PathVariable("serial") String serial) {
        return gameService.removePlayerFromGame(uuid, serial);
    }

    @PutMapping("/update/score/{uuid}/{serial}/{howmany}")
    public ResponseEntity<Object> updateDeviceScoreInGame(@PathVariable("uuid") String uuid, @PathVariable("serial") String serial,
                                                          @PathVariable("howmany") String howmany) {
        return gameService.updateDeviceScoreInGame(uuid, serial, howmany);
    }

    @PutMapping("/update/answer_finished/{uuid}/{serial}")
    public ResponseEntity<Object> updateDeviceFinishedAnsweringToQuestion(@PathVariable("uuid") String uuid, @PathVariable("serial") String serial) {
        return gameService.updateDeviceFinishedAnsweringToQuestion(uuid, serial);
    }

    @PutMapping("/update/answer/checked/{serial}")
    public ResponseEntity<Object> updateDeviceNewQuestion(@PathVariable("serial")String serial){
        return gameService.updateDeviceNewQuestion(serial);
    }

    @GetMapping("/check/question/next/available/{uuid}")
    public ResponseEntity<Object> checkNextQuestionAvailable(@PathVariable("uuid")String uuid){
        return gameService.checkNextQuestionAvailable(uuid);
    }

    @GetMapping("/all/answered/{uuid}")
    public ResponseEntity<Object> checkAllDevicesAnswered(@PathVariable("uuid") String uuid) {
        return gameService.checkAllDevicesAnswered(uuid);
    }

    @GetMapping("/find/scores/{uuid}")
    public ResponseEntity<Object> findScoresByUUID(@PathVariable("uuid") String uuid) {
        return gameService.findScoresByUUID(uuid);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Object> dropAll() { return gameService.removeAllGames(); }

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() { return gameService.findAll();
    }


}

