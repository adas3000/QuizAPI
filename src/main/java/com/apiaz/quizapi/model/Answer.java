package com.apiaz.quizapi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Answer {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "correct")
    private Choice correct;



}
