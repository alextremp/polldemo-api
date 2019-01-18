package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper;

import com.schibsted.onepunch.polldemo.domain.poll.Poll;
import com.schibsted.onepunch.polldemo.domain.poll.Proposal;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.PollDto;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.ProposalInsertRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PollMapper {

    void insert(@Param("poll") Poll poll);

    // v1 - to insert sequentially from the repository
    void insertProposal(@Param("pollId") String pollId, @Param("index") Integer index, @Param("proposal") Proposal proposal);

    // v2 - to insert all rows in a single db instruction
    void insertProposals(@Param("proposalRows") List<ProposalInsertRow> proposalRows);

    List<PollDto> selectPollDtoList(@Param("limit") Integer limit);

    PollDto selectOnePollDtoByPK(@Param("id") String id);

}
