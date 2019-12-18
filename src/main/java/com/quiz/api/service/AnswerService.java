package com.quiz.api.service;


import com.quiz.api.model.Answer;
import com.quiz.api.model.Choice;
import com.quiz.api.repo.AnswerRepository;
import com.quiz.api.repo.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private AuthService authService;

    public ResponseEntity<Object> addAnswer(Long choiceId){

        if(!checkPermissions())
            returnNoPermissions();


        Choice choice = choiceRepository.findById(choiceId).orElse(null);

        if(choice==null){
            return new ResponseEntity<>("no_choice_with_this_id", HttpStatus.BAD_REQUEST);
        }

        Answer answer = new Answer();
        answer.setCorrect(choice);

        answerRepository.save(answer);

        return new ResponseEntity<>("answer_added",HttpStatus.OK);
    }


    public ResponseEntity<Object> remove(Long id){

        if(!checkPermissions())
            returnNoPermissions();

        Answer answer = answerRepository.findById(id).orElse(null);

        if(answer==null){
           noObjectWithSuchId();
        }

        answerRepository.delete(answer);
        return new ResponseEntity<>("Deleted.",HttpStatus.OK);
    }

    public ResponseEntity<Object> findById(Long id){

        if(!checkPermissions())
            returnNoPermissions();

        Answer answer = answerRepository.findById(id).orElse(null);

        if(answer==null){
            return noObjectWithSuchId();
        }

        return new ResponseEntity<>(answer,HttpStatus.OK);
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
