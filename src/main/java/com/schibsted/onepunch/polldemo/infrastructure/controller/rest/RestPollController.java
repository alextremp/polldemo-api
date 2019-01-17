package com.schibsted.onepunch.polldemo.infrastructure.controller.rest;

import com.schibsted.onepunch.polldemo.application.poll.CreatePollUseCase;
import com.schibsted.onepunch.polldemo.application.poll.GetPollUseCase;
import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import com.schibsted.onepunch.polldemo.infrastructure.controller.rest.dto.CreatePollRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class RestPollController {

    private final CreatePollUseCase createPollUseCase;
    private final GetPollUseCase getPollUseCase;

    @Autowired
    public RestPollController(CreatePollUseCase createPollUseCase, GetPollUseCase getPollUseCase) {
        this.createPollUseCase = createPollUseCase;
        this.getPollUseCase = getPollUseCase;
    }

    @PostMapping("/poll")
    @ResponseStatus(CREATED)
    public Mono<Poll> createPoll(@Valid @RequestBody CreatePollRequest request) {
        return createPollUseCase.createPoll(request.getSubject(), request.getProposals());
    }

    @GetMapping("/poll/{pollId}")
    public Mono<Poll> getPoll(@PathVariable("pollId") String pollId) {
        return getPollUseCase.getPoll(pollId);
    }
}
