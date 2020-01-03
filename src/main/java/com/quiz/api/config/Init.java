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

        choice_b1 = new Choice();
        choice_b1.setValue("26");

        choice_b2 = new Choice();
        choice_b2.setValue("27");

        choice_b3 = new Choice();
        choice_b3.setValue("28");

        choice_b4 = new Choice();
        choice_b4.setValue("29");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);
        choiceRepository.save(choice_b3);
        choiceRepository.save(choice_b4);

        answer_b = new Answer();
        answer_b.setCorrect(choice_b3);

        answerRepository.save(answer_b);

        question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2,choice_b3,choice_b4));
        question_b.setCategory(Category.Knowledge);
        question_b.setValue("How many days February has in 1992?");

        questionRepository.save(question_b);



        choice_b1 = new Choice();
        choice_b1.setValue("Linus Torvalds");

        choice_b2 = new Choice();
        choice_b2.setValue("Bill Gates");

        choice_b3 = new Choice();
        choice_b3.setValue("Steve Jobs");

        choice_b4 = new Choice();
        choice_b4.setValue("Mark Zuckenberg");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);
        choiceRepository.save(choice_b3);
        choiceRepository.save(choice_b4);

        answer_b = new Answer();
        answer_b.setCorrect(choice_b1);

        answerRepository.save(answer_b);

        question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2,choice_b3,choice_b4));
        question_b.setCategory(Category.It);
        question_b.setValue("Who create LINUX?");

        questionRepository.save(question_b);


        //

        choice_b1 = new Choice();
        choice_b1.setValue("James Gosling");

        choice_b2 = new Choice();
        choice_b2.setValue("Dennis Ritchie");

        choice_b3 = new Choice();
        choice_b3.setValue("Niklaus Wirth");

        choice_b4 = new Choice();
        choice_b4.setValue("Bjarne Stroustrup");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);
        choiceRepository.save(choice_b3);
        choiceRepository.save(choice_b4);

        answer_b = new Answer();
        answer_b.setCorrect(choice_b4);

        answerRepository.save(answer_b);

        question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2,choice_b3,choice_b4));
        question_b.setCategory(Category.It);
        question_b.setValue("Who create C++?");

        questionRepository.save(question_b);


        choice_b1 = new Choice();
        choice_b1.setValue("James Gosling");

        choice_b2 = new Choice();
        choice_b2.setValue("Dennis Ritchie");

        choice_b3 = new Choice();
        choice_b3.setValue("Niklaus Wirth");

        choice_b4 = new Choice();
        choice_b4.setValue("Bjarne Stroustrup");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);
        choiceRepository.save(choice_b3);
        choiceRepository.save(choice_b4);

        answer_b = new Answer();
        answer_b.setCorrect(choice_b1);

        answerRepository.save(answer_b);

        question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2,choice_b3,choice_b4));
        question_b.setCategory(Category.It);
        question_b.setValue("Who create Java?");
        questionRepository.save(question_b);



        choice_b1 = new Choice();
        choice_b1.setValue("James Gosling");

        choice_b2 = new Choice();
        choice_b2.setValue("Dennis Ritchie");

        choice_b3 = new Choice();
        choice_b3.setValue("Niklaus Wirth");

        choice_b4 = new Choice();
        choice_b4.setValue("Bjarne Stroustrup");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);
        choiceRepository.save(choice_b3);
        choiceRepository.save(choice_b4);

        answer_b = new Answer();
        answer_b.setCorrect(choice_b2);

        answerRepository.save(answer_b);

        question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2,choice_b3,choice_b4));
        question_b.setCategory(Category.It);
        question_b.setValue("Who create C?");
        questionRepository.save(question_b);

        choice_b1 = new Choice();
        choice_b1.setValue("James Gosling");

        choice_b2 = new Choice();
        choice_b2.setValue("Dennis Ritchie");

        choice_b3 = new Choice();
        choice_b3.setValue("Niklaus Wirth");

        choice_b4 = new Choice();
        choice_b4.setValue("Bjarne Stroustrup");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);
        choiceRepository.save(choice_b3);
        choiceRepository.save(choice_b4);

        answer_b = new Answer();
        answer_b.setCorrect(choice_b3);

        answerRepository.save(answer_b);

        question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2,choice_b3,choice_b4));
        question_b.setCategory(Category.It);
        question_b.setValue("Who create Pascal?");
        questionRepository.save(question_b);


        choice_b1 = new Choice();
        choice_b1.setValue("Apache License");

        choice_b2 = new Choice();
        choice_b2.setValue("GNU GPL");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);


        answer_b = new Answer();
        answer_b.setCorrect(choice_b4);

        answerRepository.save(answer_b);

        question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2));
        question_b.setCategory(Category.Knowledge);
        question_b.setValue("This license allows the use of source code for both free software and proprietary software.Which one is it?");

        questionRepository.save(question_b);

        choice_b1 = new Choice();
        choice_b1.setValue("2005");
        choice_b2 = new Choice();
        choice_b2.setValue("2003");
        choice_b3 = new Choice();
        choice_b3.setValue("2004");
        choice_b4 = new Choice();
        choice_b4.setValue("2002");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);
        choiceRepository.save(choice_b3);
        choiceRepository.save(choice_b4);

        answer_b = new Answer();
        answer_b.setCorrect(choice_b3);

        answerRepository.save(answer_b);

        question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2,choice_b3,choice_b4));
        question_b.setCategory(Category.It);
        question_b.setValue("Year of facebook first release?");
        questionRepository.save(question_b);


        choice_b1 = new Choice();
        choice_b1.setValue("Yes");
        choice_b2 = new Choice();
        choice_b2.setValue("No");

        choiceRepository.save(choice_b1);
        choiceRepository.save(choice_b2);

        answer_b = new Answer();
        answer_b.setCorrect(choice_b2);

        answerRepository.save(answer_b);

        question_b = new Question();
        question_b.setAnswer(answer_b);
        question_b.setChoices(Set.of(choice_b1,choice_b2));
        question_b.setCategory(Category.It);
        question_b.setValue("Did Steve Jobs wrote at least one line of code in his entire life?");

        questionRepository.save(question_b);

    }




}
