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
