package com.quiz.api.controller;

import com.quiz.api.Annotation.Permission;
import com.quiz.api.enums.Role;
import com.quiz.api.request.QueueRequest;
import com.quiz.api.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queue")
@CrossOrigin
public class QueueController {

    @Autowired
    private QueueService queueService;

    @PostMapping("/join/{serial}/{nickname}")
    @Permission(role = Role.Public)
    public ResponseEntity<Object> pushClientToQueue(@PathVariable("serial")String serial,@PathVariable("nickname")String nickname){
        QueueRequest queueRequest = new QueueRequest();
        queueRequest.serial = serial;
        queueRequest.device_nickname = nickname;
        return queueService.pushClientToQueue(queueRequest);
    }

    @PostMapping("/{serial}")
    @Permission(role = Role.Public)
    public ResponseEntity<Object> pushClientToQueue(@PathVariable("serial")String serial){
        QueueRequest queueRequest = new QueueRequest();
        queueRequest.serial = serial;
        return queueService.pushClientToQueue(queueRequest);
    }

    @DeleteMapping("/{serial}")
    @Permission(role = Role.Public)
    public ResponseEntity<Object> popClientFromQueue(@PathVariable("serial")String serial){
        return queueService.popClientFromQueue(serial);
    }

    @DeleteMapping("/all")
    @Permission(role = Role.Admin)
    public ResponseEntity<Object> removeAllFromQueue(){
        return queueService.removeAllFromQueue();
    }


    @GetMapping("/all")
    @Permission(role = Role.Admin)
    public ResponseEntity<Object> getAllQueue(){
        return queueService.getAllQueue();
    }

    @GetMapping("/opponent/{serial}")
    @Permission(role = Role.Public)
    public ResponseEntity<Object>  getOpponent(@PathVariable("serial")String serial){
        return queueService.getOpponent(serial);
    }

}
