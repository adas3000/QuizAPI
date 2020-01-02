package com.quiz.api.service;

import com.google.common.collect.Lists;
import com.quiz.api.model.Device;
import com.quiz.api.model.Game;
import com.quiz.api.repo.DeviceRepository;
import com.quiz.api.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public ResponseEntity<Object> findGameByUUID(String uuid){

        Game game = gameRepository.findByGameUUID(uuid);

        if(game==null){
            return new ResponseEntity<>("no_such_game", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(game.getQuestions(),HttpStatus.OK);
    }

    public ResponseEntity<Object> allPlayersConnected(String uuid){

        Game game = gameRepository.findByGameUUID(uuid);

        if(game==null){
            return new ResponseEntity<>("no_such_game", HttpStatus.NOT_FOUND);
        }
        if(!(game.getPlayers().size()>1)){
            return new ResponseEntity<>("wait",HttpStatus.FOUND);
        }


        return new ResponseEntity<>(List.of("Ok"),HttpStatus.OK);
    }

    public ResponseEntity<Object> removePlayerFromGame(String uuid,String serial){

        Game game = gameRepository.findByGameUUID(uuid);

        if(game==null){
            return new ResponseEntity<>("no_such_game", HttpStatus.NOT_FOUND);
        }
        Device device = deviceRepository.findBySerialNumber(serial);

        if(device==null){
            return new ResponseEntity<>("no_such_device",HttpStatus.OK);
        }
        if(game.getPlayers().contains(device)){
            game.getPlayers().remove(device);
            if(game.getPlayers().size()==0) gameRepository.delete(game);
            return new ResponseEntity<>("removed",HttpStatus.OK);
        }
        return new ResponseEntity<>("device_and_game_has_no_relation",HttpStatus.OK);
    }


    public ResponseEntity<Object> removeAllGames(){
        gameRepository.deleteAll();
        return new ResponseEntity<>("all_data_removed_from_games",HttpStatus.OK);
    }

    public ResponseEntity<Object> findAll(){
        return new ResponseEntity<>(Lists.newArrayList(gameRepository.findAll()),HttpStatus.OK);
    }

}