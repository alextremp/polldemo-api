package com.schibsted.onepunch.polldemo.domain.poll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Poll {

    private final String id;
    private final String subject;
    private final LocalDateTime creationTime;
    private final Boolean active;
    private final List<Proposal> proposalList;

    public Poll(String id, String subject, LocalDateTime creationTime, Boolean active, List<Proposal> proposalList) {
        this.id = id;
        this.subject = subject;
        this.creationTime = creationTime;
        this.active = active;
        this.proposalList = proposalList != null ? proposalList : new ArrayList<>();
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

    public List<Proposal> getProposalList() {
        return proposalList;
    }
}
