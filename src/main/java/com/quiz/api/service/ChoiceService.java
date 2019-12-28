package com.quiz.api.service;

import com.quiz.api.model.Choice;
import com.quiz.api.repo.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChoiceService {


    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private AuthService authService;

    public ResponseEntity<Object> deleteAll(){

        if(!checkPermissions())
            return returnNoPermissions();

        choiceRepository.deleteAll();
        return new ResponseEntity<>("all_choice_data_deleted",HttpStatus.OK);
    }

    public ResponseEntity<Object> addChoice(String value){

        if(!checkPermissions())
            return returnNoPermissions();

        if(value==null || value.length()==0){
            return new ResponseEntity<>("incorrect_choice_body", HttpStatus.BAD_REQUEST);
        }

        Choice choice = new Choice();
        choice.setValue(value);

        choiceRepository.save(choice);

        return new ResponseEntity<>("Choice added",HttpStatus.OK);
    }


    public ResponseEntity<Object> remove(Long id){

        if(!checkPermissions())
            return returnNoPermissions();

        Choice choice = choiceRepository.findById(id).orElse(null);

        if(choice==null){
            return new ResponseEntity<>("no_choice_with_such_id",HttpStatus.NOT_FOUND);
        }

        choiceRepository.delete(choice);

        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    public ResponseEntity<Object> findById(Long id){

        if(!checkPermissions())
            return returnNoPermissions();

        Choice choice = choiceRepository.findById(id).orElse(null);

        if(choice==null){
            return noObjectWithSuchId();
        }

        return new ResponseEntity<>(choice,HttpStatus.OK);
    }


    private ResponseEntity<Object> noObjectWithSuchId(){
        return new ResponseEntity<>("no_object_with_such_id",HttpStatus.NOT_FOUND);
    }

    private boolean checkPermissions(){
        if(!authService.userIsAdmin())
            return false;

        return true;
    }

    private ResponseEntity<Object> returnNoPermissions(){
        return new ResponseEntity<>("no_permissions",HttpStatus.UNAUTHORIZED);
    }
}
