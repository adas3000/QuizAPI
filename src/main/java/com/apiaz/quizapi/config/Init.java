package com.apiaz.quizapi.config;

import com.apiaz.quizapi.enums.Category;
import com.apiaz.quizapi.model.Answer;
import com.apiaz.quizapi.model.Choice;
import com.apiaz.quizapi.model.Question;
import com.apiaz.quizapi.repo.AnswerRepository;
import com.apiaz.quizapi.repo.ChoiceRepository;
import com.apiaz.quizapi.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Init {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    @PostConstruct
    public void initData(){

        Choice choice_1 = new Choice();
        choice_1.setValue("24");

        Choice choice_2 = new Choice();
        choice_2.setValue("22");

        choiceRepository.save(choice_1);
        choiceRepository.save(choice_2);

        Answer answer = new Answer();
        answer.setCorrect(choice_2);

        Question question = new Question();
        question.setValue("How old Lioenl Messi was in 24th January 2010?");
        question.setChoices(Set.of(choice_1,choice_2));
        question.setCategory(Category.Sport);
        question.setAnswer(answer);

        answerRepository.save(answer);
        questionRepository.save(question);

    }




}
