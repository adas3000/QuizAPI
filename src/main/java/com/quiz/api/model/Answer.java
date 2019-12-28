package com.quiz.api.model;


import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {


    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Choice correct;


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

}
