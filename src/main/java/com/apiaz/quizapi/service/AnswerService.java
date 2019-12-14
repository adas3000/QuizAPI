package com.apiaz.quizapi.service;


import com.apiaz.quizapi.model.Answer;
import com.apiaz.quizapi.model.Choice;
import com.apiaz.quizapi.repo.AnswerRepository;
import com.apiaz.quizapi.repo.ChoiceRepository;
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


    public ResponseEntity<Object> addAnswer(Long choiceId){

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

        Answer answer = answerRepository.findById(id).orElse(null);

        if(answer==null){
           noObjectWithSuchId();
        }

        answerRepository.delete(answer);
        return new ResponseEntity<>("Deleted.",HttpStatus.OK);
    }

    public ResponseEntity<Object> findById(Long id){

        Answer answer = answerRepository.findById(id).orElse(null);

        if(answer==null){
            return noObjectWithSuchId();
        }

        return new ResponseEntity<>(answer,HttpStatus.OK);
    }

    private ResponseEntity<Object> noObjectWithSuchId(){
        return new ResponseEntity<>("no_object_with_such_id",HttpStatus.NOT_FOUND);
    }


}
