package com.quiz.api.service;

import com.google.common.collect.Lists;
import com.quiz.api.model.Device;
import com.quiz.api.model.Game;
import com.quiz.api.repo.DeviceRepository;
import com.quiz.api.repo.GameRepository;
import com.quiz.api.request.UpdateDeviceRequest;
import com.quiz.api.response.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public ResponseEntity<Object> findGameByUUID(String uuid) {

        Game game = gameRepository.findByGameUUID(uuid);

        if (game == null) {
            return new ResponseEntity<>("no_such_game", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(game.getQuestions(), HttpStatus.OK);
    }

    public ResponseEntity<Object> allPlayersConnected(String uuid) {

        Game game = gameRepository.findByGameUUID(uuid);

        if (game == null) {
            return new ResponseEntity<>("no_such_game", HttpStatus.NOT_FOUND);
        }
        if (!(game.getPlayers().size() > 1)) {
            return new ResponseEntity<>("wait", HttpStatus.FOUND);
        }


        return new ResponseEntity<>(List.of("Ok"), HttpStatus.OK);
    }

    public ResponseEntity<Object> removePlayerFromGame(String uuid, String serial) {

        Game game = gameRepository.findByGameUUID(uuid);

        if (game == null) {
            return new ResponseEntity<>("no_such_game", HttpStatus.NOT_FOUND);
        }
        Device device = deviceRepository.findBySerialNumber(serial);

        if (device == null) {
            return new ResponseEntity<>("no_such_device", HttpStatus.OK);
        }
        if (game.getPlayers().contains(device)) {
            game.getPlayers().remove(device);
            if (game.getPlayers().size() == 0) gameRepository.delete(game);
            return new ResponseEntity<>("removed", HttpStatus.OK);
        }
        return new ResponseEntity<>("device_and_game_has_no_relation", HttpStatus.OK);
    }


    public ResponseEntity<Object> removeAllGames() {
        gameRepository.deleteAll();
        return new ResponseEntity<>("all_data_removed_from_games", HttpStatus.OK);
    }

    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(Lists.newArrayList(gameRepository.findAll()), HttpStatus.OK);
    }


    public ResponseEntity<Object> updateDeviceScoreInGame(String uuid, String serial, String howmany) {
        int how;
        try {
            how = Integer.parseInt(howmany);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(List.of("incorrect_howmany"), HttpStatus.BAD_REQUEST);
        }
        //todo change lists of 1 string to just reutrn string but WATCH OUT before wasn't working maybe has to change ResponseEntity  to <String>
        //todo cd.instead of<Object>
        Device current_device = deviceRepository.findBySerialNumber(serial);
        Game game = gameRepository.findByGameUUID(uuid);

        if (game == null || current_device == null) {
            return new ResponseEntity<>(List.of("no_such_game_or_device"), HttpStatus.NOT_FOUND);
        }
        if (how < 0) {
            return new ResponseEntity<>(List.of("howmany_less_than_0"), HttpStatus.BAD_REQUEST);
        }
        if (!(game.getPlayers().contains(current_device))) {
            return new ResponseEntity<>(List.of("device_not_in_given_game"), HttpStatus.NOT_FOUND);
        }

        current_device.setCurrent_score(current_device.getCurrent_score() + how);
        deviceRepository.save(current_device);
        return new ResponseEntity<>(List.of("Updated"), HttpStatus.OK);
    }

    public ResponseEntity<Object> findScoresByUUID(String uuid) {

        Game game = gameRepository.findByGameUUID(uuid);

        if (game == null) {
            return new ResponseEntity<>(List.of("no_such_game"), HttpStatus.NOT_FOUND);
        }

        List<Score> scores = new ArrayList<>();

        for (Device d : game.getPlayers()) {
            scores.add(new Score(d.getId().toString(), d.getCurrent_score()));
        }

        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    public ResponseEntity<Object> updateDeviceFinishedAnsweringToQuestion(String uuid, String serial) {

        Device current_device = deviceRepository.findBySerialNumber(serial);
        Game game = gameRepository.findByGameUUID(uuid);

        if (game == null || current_device == null) {
            return new ResponseEntity<>(List.of("no_such_game_or_device"), HttpStatus.NOT_FOUND);
        }

        if (!(game.getPlayers().contains(current_device))) {
            return new ResponseEntity<>(List.of("device_not_in_given_game"), HttpStatus.NOT_FOUND);
        }

        current_device.setAnswered_to_question(true);
        deviceRepository.save(current_device);


        return new ResponseEntity<>(List.of("Updated"), HttpStatus.OK);
    }


    public ResponseEntity<Object> checkAllDevicesAnswered(String uuid) {
        Game game = gameRepository.findByGameUUID(uuid);

        if (game == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

        for (Device d : game.getPlayers()) {
            if (!(d.isAnswered_to_question())) {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public ResponseEntity<Object> updateDeviceReadyForNewQuestion(String serial) {

        Device device = deviceRepository.findBySerialNumber(serial);

        if (device == null) {
            return new ResponseEntity<>(List.of("no_such_device"), HttpStatus.NOT_FOUND);
        }
        device.setReady_For_Next_Question(true);
        deviceRepository.save(device);


        return new ResponseEntity<>(List.of("Updated"), HttpStatus.OK);
    }

    public ResponseEntity<Object> checkNextQuestionAvailable(String uuid) {

        Game game = gameRepository.findByGameUUID(uuid);

        if (game == null) {
            return new ResponseEntity<>("no_such_game", HttpStatus.NOT_FOUND);
        }

        for (Device d : game.getPlayers()) {
            if (!(d.isReady_For_Next_Question())) {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public ResponseEntity<Object> updateDeviceHadAnswered(String serial,Boolean value){

        Device device = deviceRepository.findBySerialNumber(serial);

        if(device==null){
            return new ResponseEntity<>(List.of("no_such_device"),HttpStatus.NOT_FOUND);
        }

        device.setAnswered_to_question(value);
        deviceRepository.save(device);
        return new ResponseEntity<>(List.of("Updated"),HttpStatus.OK);
    }

    public ResponseEntity<Object> updateDeviceReadyForNextQuestion(String serial,Boolean value) {

        Device device = deviceRepository.findBySerialNumber(serial);

        if (device == null) {
            return new ResponseEntity<>(List.of("no_such_device"), HttpStatus.NOT_FOUND);
        }
        device.setReady_For_Next_Question(value);
        deviceRepository.save(device);
        return new ResponseEntity<>(List.of("Updated"),HttpStatus.OK);
    }

    public ResponseEntity<Object> updateDevice(UpdateDeviceRequest updateDeviceRequest){

        Device device = deviceRepository.findBySerialNumber(updateDeviceRequest.serial);

        if(device==null){
            return new ResponseEntity<>(List.of("no_such_device"),HttpStatus.NOT_FOUND);
        }
        device.setReady_For_Next_Question(updateDeviceRequest.ready_For_Next_Question);
        device.setAnswered_to_question(updateDeviceRequest.answered_to_question);

        deviceRepository.save(device);
        return new ResponseEntity<>(List.of("Update"),HttpStatus.OK);
    }

}
