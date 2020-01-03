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

//@Component
public class Init {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

  //  @PostConstruct
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

        Choice choice_b1 = new Choice();
        choice_b1.setValue("60's of the 20th century");

        Choice choice_b2 = new Choice();
        choice_b2.setValue("50's of the 20th century");

        Choice choice_b3 = new Choice();
        choice_b3.setValue("Begin of the 20th century");

        Choice choice_b4 = new Choice();
        choice_b4.setValue("40's of the 20th century");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);
        choiceRepository.save(choice_b3);
        choiceRepository.save(choice_b4);

        Answer answer_b = new Answer();
        answer_b.setCorrect(choice_b4);

        answerRepository.save(answer_b);

        Question question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2,choice_b3,choice_b4));
        question_b.setCategory(Category.It);
        question_b.setValue("IT beginnings are considered to:");

        questionRepository.save(question_b);


        







    }




}
