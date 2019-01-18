package com.schibsted.onepunch.polldemo.domain.poll;

public class Vote {

    private final String id;
    private final String voter;

    public Vote(String id, String voter) {
        this.id = id;
        this.voter = voter;
    }

    public String getId() {
        return id;
    }

    public String getVoter() {
        return voter;
    }
}
