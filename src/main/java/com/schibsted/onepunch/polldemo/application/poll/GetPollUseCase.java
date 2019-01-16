package com.schibsted.onepunch.polldemo.application.poll;

import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import com.schibsted.onepunch.polldemo.domain.poll.PollNotFoundException;
import com.schibsted.onepunch.polldemo.domain.poll.PollRepository;
import reactor.core.publisher.Mono;

public class GetPollUseCase {

    private final PollRepository pollRepository;

    public GetPollUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Mono<Poll> getPoll(String pollId) {
        return pollRepository.findPollById(pollId)
                .switchIfEmpty(Mono.error(new PollNotFoundException("Poll not found: " + pollId)));
    }
}
