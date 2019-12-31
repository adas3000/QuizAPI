package com.quiz.api.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.quiz.api.enums.Category;
import com.quiz.api.model.Device;
import com.quiz.api.model.Game;
import com.quiz.api.repo.DeviceRepository;
import com.quiz.api.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QueueService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private QuestionService questionService;

    public ResponseEntity<Object> pushClientToQueue(String serialNumber) {

        Device d = deviceRepository.findBySerialNumber(serialNumber);

        if (d != null) {
            return new ResponseEntity<>(List.of("already_in_queue"), HttpStatus.BAD_REQUEST);
        }

        d = new Device();
        d.setSerialNumber(serialNumber);

        deviceRepository.save(d);
        return new ResponseEntity<>(List.of("added_to_queue"), HttpStatus.OK);
    }

    public ResponseEntity<Object> popClientFromQueue(String serialNumber) {

        Device d = deviceRepository.findBySerialNumber(serialNumber);

        if (d == null) {
            return new ResponseEntity<>(List.of("not_in_queue"), HttpStatus.BAD_REQUEST);
        }

        deviceRepository.delete(d);
        return new ResponseEntity<>(List.of("deleted_from_queue"), HttpStatus.OK);
    }


    public ResponseEntity<Object> getOpponent(String serialNumber) {

        Device d = deviceRepository.findBySerialNumber(serialNumber);

        if (d == null) {
            return new ResponseEntity<>("no_such_device_in_queue", HttpStatus.NOT_FOUND);
        }

        List<Game> games = Lists.newArrayList(gameRepository.findAll());
        Game game = null;

        for(Game g : games){
            if(g.getPlayers().size()==1){
                game = g;
            }
        }

        String gameUUID;
        if (game == null) {
            gameUUID = UUID.randomUUID().toString();
            game = new Game();
            game.getPlayers().add(d);
            game.setGameUUID(gameUUID);
            game.setQuestions(questionService.getRandomQuestionList(Category.All,10));
        } else {
            gameUUID = game.getGameUUID();
        }

        return new ResponseEntity<>(gameUUID, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllQueue() {
        //todo auth (only admin may use it )

        return new ResponseEntity<>(deviceRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> removeAllFromQueue() {
        //todo auth (only admin may use it)
        deviceRepository.deleteAll();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

}
