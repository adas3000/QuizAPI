package com.quiz.api.controller;


import com.quiz.api.Annotation.Permission;
import com.quiz.api.enums.Role;
import com.quiz.api.service.ChoiceService;
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

    @Permission(role = Role.Admin)
    @PostMapping
    public ResponseEntity<Object> addChoice(@RequestBody @NonNull String value){
        return choiceService.addChoice(value);
    }

    @Permission(role = Role.Admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable @NonNull Long id){
        return choiceService.remove(id);
    }

    @Permission(role = Role.Admin)
    @GetMapping
    public ResponseEntity<Object> findById(@RequestParam @NonNull Long id){
        return choiceService.findById(id);
    }


}
