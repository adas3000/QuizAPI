package com.quiz.api.controller;

import com.quiz.api.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @PostMapping
    public ResponseEntity<Object> pushClientToQueue(@RequestBody @NonNull String serial){
        return queueService.pushClientToQueue(serial);
    }


    @DeleteMapping("/{serial}")
    public ResponseEntity<Object> popClientFromQueue(@PathVariable("serial")String serial){
        return queueService.popClientFromQueue(serial);
    }
    
}
