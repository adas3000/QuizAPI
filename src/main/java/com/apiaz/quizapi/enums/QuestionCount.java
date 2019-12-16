package com.apiaz.quizapi.enums;

public enum QuestionCount {
    Five(5),Ten(10),Fifteen(15),Twenty(20);

    private int count;

    QuestionCount(int count){
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
