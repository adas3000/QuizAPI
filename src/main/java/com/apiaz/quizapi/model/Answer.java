package com.apiaz.quizapi.model;


import javax.persistence.*;

@Entity
public class Answer {


    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Choice correct;

    @OneToOne
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Choice getCorrect() {
        return correct;
    }

    public void setCorrect(Choice correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
