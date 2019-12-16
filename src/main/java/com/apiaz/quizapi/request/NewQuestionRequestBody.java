package com.apiaz.quizapi.request;

/**
 *  asnwerID - id in choiceValues
 */
public class NewQuestionRequestBody {

    public String value;
    public int answerId;
    public String [] choiceValues;
    public String category;
}
