package com.quiz.api.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Choice {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "value")
    private String value;

    public Choice(String value) {
        this.value = value;
    }

    public Choice(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
