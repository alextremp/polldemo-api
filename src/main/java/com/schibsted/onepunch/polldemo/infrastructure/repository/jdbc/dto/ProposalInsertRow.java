package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto;

public class ProposalInsertRow {

    private final String id;
    private final String pollId;
    private final Integer index;
    private final String subject;

    public ProposalInsertRow(String id, String pollId, Integer index, String subject) {
        this.id = id;
        this.pollId = pollId;
        this.index = index;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public String getPollId() {
        return pollId;
    }

    public Integer getIndex() {
        return index;
    }

    public String getSubject() {
        return subject;
    }
}
