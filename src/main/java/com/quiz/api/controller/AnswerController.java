package com.quiz.api.controller;

import com.quiz.api.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<Object> addAnswer(@RequestBody @NonNull Long choice_id){
        return answerService.addAnswer(choice_id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeById(@PathVariable @RequestBody @NonNull Long answer_id){
        return answerService.remove(answer_id);
    }

    @GetMapping
    public ResponseEntity<Object> findById(@RequestParam @NonNull Long answer_id){
        return answerService.findById(answer_id);
    }





}
