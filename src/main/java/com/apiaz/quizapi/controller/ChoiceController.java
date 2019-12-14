package com.apiaz.quizapi.controller;


import com.apiaz.quizapi.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/choice")
public class ChoiceController {

    @Autowired
    private ChoiceService choiceService;

    @PostMapping
    public ResponseEntity<Object> addChoice(@RequestBody @NonNull String value){
        return choiceService.addChoice(value);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable @NonNull Long id){
        return choiceService.remove(id);
    }

    @GetMapping
    public ResponseEntity<Object> findById(@RequestParam @NonNull Long id){
        return choiceService.findById(id);
    }


}
