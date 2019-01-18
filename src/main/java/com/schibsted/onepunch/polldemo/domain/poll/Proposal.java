package com.schibsted.onepunch.polldemo.domain.poll;

import java.util.ArrayList;
import java.util.List;

public class Proposal {

    private final String id;
    private final String subject;
    private final List<Vote> voteList;

    public Proposal(String id, String subject, List<Vote> voteList) {
        this.id = id;
        this.subject = subject;
        this.voteList = voteList != null ? voteList : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }
}
