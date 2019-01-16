package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc;

import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import com.schibsted.onepunch.polldemo.domain.poll.PollRepository;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper.PollMapper;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class JdbcPollRepository implements PollRepository {

    private final PollMapper pollMapper;

    public JdbcPollRepository(PollMapper pollMapper) {
        this.pollMapper = pollMapper;
    }

    @Override
    public Mono<Poll> save(Poll poll) {
        return Mono.fromRunnable(() -> pollMapper.insert(poll))
                .subscribeOn(Schedulers.elastic())
                .publishOn(Schedulers.parallel())
                .then(Mono.just(poll));
    }

    @Override
    public Mono<Poll> findPollById(String pollId) {
        return Mono.fromCallable(() -> pollMapper.selectOneByPK(pollId))
                .subscribeOn(Schedulers.elastic())
                .publishOn(Schedulers.parallel());
    }
}
