package com.schibsted.onepunch.polldemo.domain.poll;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public class PollFactory {

    public Mono<Poll> createPoll(String subject) {
        return Mono.fromCallable(() -> UUID.randomUUID().toString())
                .map(id -> new Poll(id, subject, LocalDateTime.now(), true));
    }
}
