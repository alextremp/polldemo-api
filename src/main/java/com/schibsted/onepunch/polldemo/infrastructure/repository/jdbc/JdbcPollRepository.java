package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc;

import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import com.schibsted.onepunch.polldemo.domain.poll.Proposal;
import com.schibsted.onepunch.polldemo.domain.poll.Vote;
import com.schibsted.onepunch.polldemo.domain.poll.PollFactory;
import com.schibsted.onepunch.polldemo.domain.poll.PollRepository;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.PollDto;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.ProposalDto;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.ProposalInsertRow;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.VoteDto;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper.PollMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JdbcPollRepository extends AbstractTransactionalJdbcRepository implements PollRepository {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcPollRepository.class);

    private final PollMapper pollMapper;
    private final PollFactory pollFactory;

    public JdbcPollRepository(TransactionTemplate transactionTemplate, PollMapper pollMapper, PollFactory pollFactory) {
        super(transactionTemplate);
        this.pollMapper = pollMapper;
        this.pollFactory = pollFactory;
    }

    @Override
    public Mono<Poll> save(Poll poll) {
        return runnableTx(() -> {
            pollMapper.insert(poll);
            List<ProposalInsertRow> proposalRows = IntStream.range(0, poll.getProposalList().size())
                    .mapToObj(index -> new ProposalInsertRow(
                            poll.getProposalList().get(index).getId(),
                            poll.getId(),
                            index,
                            poll.getProposalList().get(index).getSubject()
                    ))
                    .collect(Collectors.toList());
            pollMapper.insertProposals(proposalRows);
        })
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
        LOG.debug(">> mapping Poll: {}", pollDto.getId());
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
