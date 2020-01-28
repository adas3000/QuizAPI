package com.quiz.api.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.quiz.api.enums.Category;
import com.quiz.api.model.Device;
import com.quiz.api.model.Game;
import com.quiz.api.repo.DeviceRepository;
import com.quiz.api.repo.GameRepository;
import com.quiz.api.request.NewRoomRequest;
import com.quiz.api.request.QueueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QueueService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private QuestionService questionService;

    public ResponseEntity<Object> pushClientToQueue(QueueRequest queueRequest) {

        String serialNumber = queueRequest.serial;

        Device d = deviceRepository.findBySerialNumber(serialNumber);

        if (d != null) {
            return new ResponseEntity<>(List.of("already_in_queue"), HttpStatus.BAD_REQUEST);
        }
        d = new Device();
        d.setSerialNumber(serialNumber);
        d.setDevice_nickname(queueRequest.device_nickname);
        deviceRepository.save(d);

        return new ResponseEntity<>(List.of("added_to_queue"), HttpStatus.OK);
    }

    public ResponseEntity<Object> popClientFromQueue(String serialNumber) {

        Device d = deviceRepository.findBySerialNumber(serialNumber);

        if (d == null) {
            return new ResponseEntity<>(List.of("not_in_queue"), HttpStatus.BAD_REQUEST);
        }

        deviceRepository.delete(d);
        System.out.println("Opponent went out from queue");
        return new ResponseEntity<>(List.of("deleted_from_queue"), HttpStatus.OK);
    }


    public ResponseEntity<Object> getOpponent(String serialNumber,String playerCount) {

        int p_count;
        try{
            p_count = Integer.parseInt(playerCount);
        }
        catch(NumberFormatException e){
            e.fillInStackTrace();
            return new ResponseEntity<>("wrong_player_count_num",HttpStatus.BAD_REQUEST);
        }


        Device d = deviceRepository.findBySerialNumber(serialNumber);

        if (d == null) {
            return new ResponseEntity<>("no_such_device_in_queue", HttpStatus.NOT_FOUND);
        }

        List<Game> games = Lists.newArrayList(gameRepository.findAll());

        Game game = null;

        for(Game g : games){
            if(g.getPlayers().size()<g.getPlayersCount()){
                game = g;
            }
        }

        String gameUUID;
        if (game == null) {
            gameUUID = UUID.randomUUID().toString();
            game = new Game();
            game.getPlayers().add(d);
            game.setGameUUID(gameUUID);
            game.setQuestions(questionService.getRandomQuestionList(Category.All,5)); //todo do player category choosing and question count
        } else {
            gameUUID = game.getGameUUID();
            game.getPlayers().add(d);
        }
        gameRepository.save(game);
        deviceRepository.save(d);

        return new ResponseEntity<>(List.of(gameUUID), HttpStatus.OK);
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

    public ResponseEntity<Object> createNewRoom(NewRoomRequest newRoomRequest){

        Device d = deviceRepository.findBySerialNumber(newRoomRequest.serial);

        if (d == null) {
            return new ResponseEntity<>("no_such_device", HttpStatus.NOT_FOUND);
        }

        int questionCount = newRoomRequest.questionCount;
        int playersCount = newRoomRequest.playerCount;
        Category category;
        try {
            category = Category.valueOf(newRoomRequest.category);
        }
        catch(IllegalArgumentException e){
            e.fillInStackTrace();
            return new ResponseEntity<>("no_such_category",HttpStatus.OK);
        }

        if(questionCount<0 || playersCount < 0) {
            return new ResponseEntity<>("invalid_data",HttpStatus.BAD_REQUEST);
        }

        String gameUUID = UUID.randomUUID().toString();
        Game game = new Game();
        game.getPlayers().add(d);
        game.setGameUUID(gameUUID);
        game.setQuestions(questionService.getRandomQuestionList(category,questionCount));
        game.setPlayersCount(playersCount);

        gameRepository.save(game);

        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    public ResponseEntity<Object> findDuel(String serial){

        Device device = deviceRepository.findBySerialNumber(serial);

        if(device==null){
            return new ResponseEntity<>("no_such_device",HttpStatus.BAD_REQUEST);
        }

        List<Game> games = Lists.newArrayList(gameRepository.findAll());

        Game game = null;

        for(Game g : games){
            if(g.getPlayers().size()<g.getPlayersCount()){
                game = g;
            }
        }
        if(game==null){
            return new ResponseEntity<>("no_rooms",HttpStatus.ACCEPTED);
        }
        String gameUUID = game.getGameUUID();
        game.getPlayers().add(device);
        deviceRepository.save(device);


        return new ResponseEntity<>(gameUUID,HttpStatus.OK);
    }

}
