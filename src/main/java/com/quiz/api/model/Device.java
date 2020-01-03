package com.quiz.api.model;

import javax.persistence.*;

@Entity
@Table
public class Device {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "serialnumber")
    private String serialNumber;

    @Column(name = "opponentSerialNumber")
    private String opponentSerialNumber=null;

    @Column(name = "is_choosing_category")
    private boolean is_choosing_category=false;

    private int current_score = 0;

    public int getCurrent_score() {
        return current_score;
    }

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

    public String getOpponentSerialNumber() {
        return opponentSerialNumber;
    }

    public void setOpponentSerialNumber(String opponentSerialNumber) {
        this.opponentSerialNumber = opponentSerialNumber;
    }

    public boolean isIs_choosing_category() {
        return is_choosing_category;
    }

    public void setIs_choosing_category(boolean is_choosing_category) {
        this.is_choosing_category = is_choosing_category;
    }
}
