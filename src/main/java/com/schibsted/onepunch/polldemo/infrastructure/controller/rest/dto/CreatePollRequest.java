package com.schibsted.onepunch.polldemo.infrastructure.controller.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CreatePollRequest {

    @NotEmpty(message = "subject must be a not empty text")
    private String subject;

    @Size(min = 2, message = "the poll must have at least 2 proposals")
    private List<String> proposals = new ArrayList<>();

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getProposals() {
        return proposals;
    }

    public void setProposals(List<String> proposals) {
        this.proposals = proposals;
    }
}
