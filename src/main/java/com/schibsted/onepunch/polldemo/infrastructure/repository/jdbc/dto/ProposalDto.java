package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto;

import java.util.ArrayList;
import java.util.List;

public class ProposalDto {

    private String id;
    private String subject;

    private List<VoteDto> voteDtoList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<VoteDto> getVoteDtoList() {
        return voteDtoList;
    }

    public void setVoteDtoList(List<VoteDto> voteDtoList) {
        this.voteDtoList = voteDtoList;
    }
}
