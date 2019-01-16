package com.schibsted.onepunch.polldemo.infrastructure.controller.rest.dto;

import javax.validation.constraints.NotEmpty;

public class CreatePollRequest {

    @NotEmpty(message = "subject must be a not empty text")
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
