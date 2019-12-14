package com.apiaz.quizapi.controller;

import com.apiaz.quizapi.request.NewQuestionRequest;
import com.apiaz.quizapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Object> addQuestion(@RequestBody @NonNull NewQuestionRequest newQuestionRequest){
        return questionService.addQuestion(newQuestionRequest);
    }

    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody @NonNull Long id){
        return questionService.remove(id);
    }

    @GetMapping
    public ResponseEntity<Object> findById(@RequestParam @NonNull Long id){
        return questionService.findById(id);
    }




}
