package com.schibsted.onepunch.polldemo.domain.poll;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PollFactory {

    public Mono<Poll> createPoll(String subject, List<String> proposals) {
        return Mono.fromCallable(() -> UUID.randomUUID().toString())
                .map(id -> new Poll(
                        id,
                        subject,
                        LocalDateTime.now(),
                        true,
                        proposals.stream().map(proposalSubject -> new Proposal(
                                UUID.randomUUID().toString(),
                                proposalSubject,
                                null
                        )).collect(Collectors.toList())
                ));
    }

    public Mono<Poll> createPoll(String id, String subject, LocalDateTime creationTime, Boolean active, List<Proposal> proposalList) {
        return Mono.fromCallable(() -> new Poll(id, subject, creationTime, active, proposalList));
    }
}
