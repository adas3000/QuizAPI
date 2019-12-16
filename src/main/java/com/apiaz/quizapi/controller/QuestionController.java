package com.apiaz.quizapi.controller;

import com.apiaz.quizapi.request.NewQuestionRequest;
import com.apiaz.quizapi.request.NewQuestionRequestBody;
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

    @PostMapping("/add")
    public ResponseEntity<Object> addQuestion(@RequestBody @NonNull NewQuestionRequest newQuestionRequest){
        return questionService.addQuestion(newQuestionRequest);
    }

    @PostMapping("/add/manual")
    public ResponseEntity<Object> addQuestion(@RequestBody @NonNull NewQuestionRequestBody newQuestionRequestBody){
        return questionService.addQuestion(newQuestionRequestBody);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable @NonNull Long id){
        return questionService.remove(id);
    }

    @GetMapping
    public ResponseEntity<Object> findById(@RequestParam @NonNull Long id){
        return questionService.findById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById_2(@PathVariable Long id){
        return questionService.findById(id);
    }


}
