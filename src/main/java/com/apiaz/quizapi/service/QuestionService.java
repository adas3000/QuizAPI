package com.apiaz.quizapi.service;

import com.apiaz.quizapi.model.Answer;
import com.apiaz.quizapi.model.Choice;
import com.apiaz.quizapi.model.Question;
import com.apiaz.quizapi.repo.AnswerRepository;
import com.apiaz.quizapi.repo.ChoiceRepository;
import com.apiaz.quizapi.repo.QuestionRepository;
import com.apiaz.quizapi.request.NewQuestionRequest;
import com.apiaz.quizapi.request.NewQuestionRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public ResponseEntity<Object> addQuestion(NewQuestionRequest questionRequest) {

        Answer answer = answerRepository.findById(questionRequest.answerId).orElse(null);

        if (answer == null) {
            return new ResponseEntity<>("no_answer_with_such_id", HttpStatus.NOT_FOUND);
        }

        Set<Choice> choices = new HashSet<>();

        for (Long i : questionRequest.choicesId) {

            Choice choice = choiceRepository.findById(i).orElse(null);

            if (choice == null) {
                return new ResponseEntity<>("no_choice_with_such_id", HttpStatus.NOT_FOUND);
            }
            choices.add(choice);
        }

        if (questionRequest.value == null || questionRequest.value.length() < 6) {
            return new ResponseEntity<>("question_length_too_short_write_at_least_6_chars", HttpStatus.BAD_REQUEST);
        }

        Question question = new Question();
        question.setAnswer(answer);
        question.setChoices(choices);
        question.setValue(questionRequest.value);

        questionRepository.save(question);

        return new ResponseEntity<>("question_added", HttpStatus.OK);
    }

    public ResponseEntity<Object> addQuestion(NewQuestionRequestBody newQuestionRequestBody){

        return null;
    }


    public ResponseEntity<Object> remove(Long id) {

        Question question = questionRepository.findById(id).orElse(null);

        if (question == null) {
            return new ResponseEntity<>("no_question_with_such_id", HttpStatus.NOT_FOUND);
        }

        questionRepository.delete(question);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    public ResponseEntity<Object> findById(Long id) {

        Question question = questionRepository.findById(id).orElse(null);

        if (question == null) {
            return new ResponseEntity<>("no_question_with_such_id", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(question,HttpStatus.OK);
    }


}
