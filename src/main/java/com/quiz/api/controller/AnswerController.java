package com.quiz.api.controller;

import com.quiz.api.Annotation.Permission;
import com.quiz.api.enums.Role;
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

    @Permission(role = Role.Admin)
    @PostMapping
    public ResponseEntity<Object> addAnswer(@RequestBody @NonNull Long choice_id){
        return answerService.addAnswer(choice_id);
    }

    @Permission(role = Role.Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeById(@PathVariable @RequestBody @NonNull Long answer_id){
        return answerService.remove(answer_id);
    }

    @Permission(role = Role.Admin)
    @GetMapping
    public ResponseEntity<Object> findById(@RequestParam @NonNull Long answer_id){
        return answerService.findById(answer_id);
    }





}
