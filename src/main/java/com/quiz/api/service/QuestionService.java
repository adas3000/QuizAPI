package com.quiz.api.service;

import com.quiz.api.enums.Category;
import com.quiz.api.enums.Role;
import com.quiz.api.model.Answer;
import com.quiz.api.model.Choice;
import com.quiz.api.model.Question;
import com.quiz.api.model.User;
import com.quiz.api.repo.AnswerRepository;
import com.quiz.api.repo.ChoiceRepository;
import com.quiz.api.repo.QuestionRepository;
import com.quiz.api.request.NewQuestionRequest;
import com.quiz.api.request.NewQuestionRequestBody;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AuthService authService;


    public ResponseEntity<Object> addQuestion(NewQuestionRequest questionRequest) {


        if(!checkPermissions())
            return returnNoPermissions();

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

    public ResponseEntity<Object> addQuestion(NewQuestionRequestBody newQuestionRequestBody) {
        if(!checkPermissions())
            return returnNoPermissions();

        Question question = questionRepository.findByValue(newQuestionRequestBody.value).orElse(null);

        if (question != null) {
            return new ResponseEntity<>("question_already_exist", HttpStatus.BAD_REQUEST);
        } else if (newQuestionRequestBody.choiceValues == null || newQuestionRequestBody.category == null ||
                newQuestionRequestBody.value == null) {
            return new ResponseEntity<>("value_cannot_be_empty", HttpStatus.BAD_REQUEST);
        } else if (newQuestionRequestBody.answerId < 0 || newQuestionRequestBody.answerId > newQuestionRequestBody.choiceValues.length - 1) {
            return new ResponseEntity<>("wrong_answer_id_value", HttpStatus.BAD_REQUEST);
        }


        String categoryCapitalized = getCategoryStringCapitalized(newQuestionRequestBody.category);

        try {
            Category c = Category.valueOf(categoryCapitalized);

            Choice correct_choice = new Choice(newQuestionRequestBody.choiceValues[newQuestionRequestBody.answerId]);

            Answer answer = new Answer();
            answer.setCorrect(correct_choice);

            choiceRepository.save(correct_choice);
            answerRepository.save(answer);

            Set<Choice> choices = new HashSet<>();

            for (String s : newQuestionRequestBody.choiceValues) {
                Choice ch = new Choice(s);
                choices.add(ch);
                choiceRepository.save(ch);
            }

            question = new Question();
            question.setCategory(c);
            question.setValue(newQuestionRequestBody.value);
            question.setAnswer(answer);
            question.setChoices(choices);

            questionRepository.save(question);

        } catch (IllegalArgumentException e) {
            e.fillInStackTrace();
            return new ResponseEntity<>("no_such_category", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("question_added", HttpStatus.OK);
    }


    public ResponseEntity<Object> remove(Long id) {
        if(!checkPermissions())
            return returnNoPermissions();

        Question question = questionRepository.findById(id).orElse(null);

        if (question == null) {
            return new ResponseEntity<>("no_question_with_such_id", HttpStatus.NOT_FOUND);
        }

        questionRepository.delete(question);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    public ResponseEntity<Object> findById(Long id) {
        if(!checkPermissions())
            return returnNoPermissions();

        Question question = questionRepository.findById(id).orElse(null);

        if (question == null) {
            return new ResponseEntity<>("no_question_with_such_id", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    public ResponseEntity<Object> findAllByCategory(String category) {


        return null;
    }

    public ResponseEntity<Object> findAll() {
        if(!checkPermissions())
            return returnNoPermissions();
        return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getRandomQuestions(String category, String count) {

        try {
            String categoryCapitalized = getCategoryStringCapitalized(category);

            int c_count = Integer.parseInt(count);
            Category c_category = Category.valueOf(categoryCapitalized);

            List<Question> questions;
            if (c_category.equals(Category.All))
                questions = Lists.newArrayList(questionRepository.findAll());
            else
                questions = questionRepository.findAllByCategory(c_category);

            if (questions.size() < c_count) {
                return new ResponseEntity<>("no_enough_questions", HttpStatus.BAD_REQUEST);
            }

            List<Question> random = new ArrayList<>();
            for (int i = 0; i < c_count; i++) {
                int rand_int = ThreadLocalRandom.current().nextInt(0, questions.size());
                random.add(questions.get(rand_int));
                questions.remove(rand_int);
            }

            return new ResponseEntity<>(random, HttpStatus.OK);
        } catch (NumberFormatException e) {
            e.fillInStackTrace();
            return new ResponseEntity<>("count_have_to_be_digit", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            e.fillInStackTrace();
            return new ResponseEntity<>("no_such_category", HttpStatus.BAD_REQUEST);
        }
    }

    public List<Question> getRandomQuestionList(Category category,int count){
        List<Question> questions;
        if (category.equals(Category.All))
            questions = Lists.newArrayList(questionRepository.findAll());
        else
            questions = questionRepository.findAllByCategory(category);
        
        List<Question> random = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int rand_int = ThreadLocalRandom.current().nextInt(0, questions.size());
            random.add(questions.get(rand_int));
            questions.remove(rand_int);
        }
        return random;
    }

    public ResponseEntity<Object> deleteAll(){

        if(!checkPermissions())
            return returnNoPermissions();

        questionRepository.deleteAll();
        return new ResponseEntity<>("all_question_data_deleted",HttpStatus.OK);
    }

    private String getCategoryStringCapitalized(String category) {
        return category.substring(0, 1).toUpperCase() + category.substring(1);
    }

    private boolean checkPermissions(){

        User u = authService.getLoggedUser();
        if(u==null || u.getRole()== Role.Admin){
            return false;
        }
        return true;
    }

    private ResponseEntity<Object> returnNoPermissions(){
        return new ResponseEntity<>("no_permissions",HttpStatus.UNAUTHORIZED);
    }

}
