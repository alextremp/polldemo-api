package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto;

public class VoteDto {

    private String id;
    private String voterId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }
}
