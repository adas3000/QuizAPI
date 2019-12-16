package com.quiz.api.config;

import com.quiz.api.enums.Category;
import com.quiz.api.model.Answer;
import com.quiz.api.model.Choice;
import com.quiz.api.model.Question;
import com.quiz.api.repo.AnswerRepository;
import com.quiz.api.repo.ChoiceRepository;
import com.quiz.api.repo.QuestionRepository;
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
