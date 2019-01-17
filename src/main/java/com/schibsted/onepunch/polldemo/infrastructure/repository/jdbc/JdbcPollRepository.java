package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc;

import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import com.schibsted.onepunch.polldemo.domain.poll.Proposal;
import com.schibsted.onepunch.polldemo.domain.poll.Vote;
import com.schibsted.onepunch.polldemo.domain.poll.PollFactory;
import com.schibsted.onepunch.polldemo.domain.poll.PollRepository;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.PollDto;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.ProposalDto;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.VoteDto;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper.PollMapper;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.stream.Collectors;

public class JdbcPollRepository implements PollRepository {

    private final PollMapper pollMapper;
    private final PollFactory pollFactory;

    public JdbcPollRepository(PollMapper pollMapper, PollFactory pollFactory) {
        this.pollMapper = pollMapper;
        this.pollFactory = pollFactory;
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
        return Mono.fromCallable(() -> pollMapper.selectOnePollDtoByPK(pollId))
                .subscribeOn(Schedulers.elastic())
                .publishOn(Schedulers.parallel())
                .flatMap(this::mapPollDto2Poll);
    }

    private Mono<Poll> mapPollDto2Poll(PollDto pollDto) {
        return pollFactory.createPoll(
                pollDto.getId(),
                pollDto.getSubject(),
                pollDto.getCreationTime(),
                pollDto.getActive(),
                pollDto.getProposalDtoList()
                        .stream()
                        .map(this::mapProposalDto2Proposal)
                        .collect(Collectors.toList())
        );
    }

    private Proposal mapProposalDto2Proposal(ProposalDto proposalDto) {
        return new Proposal(
                proposalDto.getId(),
                proposalDto.getSubject(),
                proposalDto.getVoteDtoList()
                        .stream()
                        .map(this::mapVoteDto2Vote)
                        .collect(Collectors.toList())
        );
    }

    private Vote mapVoteDto2Vote(VoteDto voteDto) {
        return new Vote(voteDto.getId(), voteDto.getVoterId());
    }
}
