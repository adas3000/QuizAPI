package com.quiz.api.model;

import javax.persistence.*;

@Entity
@Table(name="device")
public class Device {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "serialnumber")
    private String serialNumber;

    private boolean answered_to_question = false;

    private boolean ready_For_Next_Question = false;

    private int current_score = 0;

    public boolean isReady_For_Next_Question() {
        return ready_For_Next_Question;
    }

    public void setReady_For_Next_Question(boolean ready_For_Next_Question) {
        this.ready_For_Next_Question = ready_For_Next_Question;
    }

    public boolean isAnswered_to_question() {
        return answered_to_question;
    }

    public void setAnswered_to_question(boolean answered_to_question) {
        this.answered_to_question = answered_to_question;
    }

    public int getCurrent_score() { return current_score; }

    public void setCurrent_score(int current_score) {
        this.current_score = current_score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
