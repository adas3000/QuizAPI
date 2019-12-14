package com.apiaz.quizapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Question {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "content")
    private String value;

    @Column
    private Set<Choice> choices = new HashSet<>();




}
