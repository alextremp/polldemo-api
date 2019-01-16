package com.schibsted.onepunch.polldemo.application.poll;

import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import com.schibsted.onepunch.polldemo.domain.poll.PollFactory;
import com.schibsted.onepunch.polldemo.domain.poll.PollRepository;
import reactor.core.publisher.Mono;

public class CreatePollUseCase {

    private final PollFactory pollFactory;
    private final PollRepository pollRepository;

    public CreatePollUseCase(PollFactory pollFactory, PollRepository pollRepository) {
        this.pollFactory = pollFactory;
        this.pollRepository = pollRepository;
    }

    public Mono<Poll> createPoll(String subject) {
        return pollFactory.createPoll(subject)
                .flatMap(pollRepository::save);
    }

}
