package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PollDto {

    private String id;
    private String subject;
    private LocalDateTime creationTime;
    private Boolean active;

    private List<ProposalDto> proposalDtoList = new ArrayList<>();

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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<ProposalDto> getProposalDtoList() {
        return proposalDtoList;
    }

    public void setProposalDtoList(List<ProposalDto> proposalDtoList) {
        this.proposalDtoList = proposalDtoList;
    }
}
