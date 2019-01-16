package com.schibsted.onepunch.polldemo.domain.poll;

import reactor.core.publisher.Mono;

public interface PollRepository {
    Mono<Poll> save(Poll poll);

    Mono<Poll> findPollById(String pollId);
}
