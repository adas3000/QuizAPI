package com.quiz.api.request;

public class UpdateDeviceRequest {
    public final String serial;
    public final Boolean answered_to_question;
    public final Boolean ready_For_Next_Question;

    public UpdateDeviceRequest(String serial, Boolean answered_to_question, Boolean ready_For_Next_Question) {
        this.serial = serial;
        this.answered_to_question = answered_to_question;
        this.ready_For_Next_Question = ready_For_Next_Question;
    }
}
