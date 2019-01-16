package com.schibsted.onepunch.polldemo.domain.poll;

import java.time.LocalDateTime;

public class Poll {

    private final String id;
    private final String subject;
    private final LocalDateTime creationTime;
    private final Boolean active;

    public Poll(String id, String subject, LocalDateTime creationTime, Boolean active) {
        this.id = id;
        this.subject = subject;
        this.creationTime = creationTime;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Boolean getActive() {
        return active;
    }
}
