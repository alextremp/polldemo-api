package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper;

import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.dto.PollDto;
import com.schibsted.onepunch.polldemo.integration.AbstractIntegrationTestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PollMapperTest extends AbstractIntegrationTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(PollMapperTest.class);

    @Autowired
    private PollMapper pollMapper;

    @Test
    @Sql({"/fixtures/db/truncates.sql", "/fixtures/db/inserts-03-polls.sql"})
    public void shouldLoadAListOfPollEntities() {
        LOG.info("selectPollEntities >> start");

        List<PollDto> pollDtoList = pollMapper.selectPollDtoList(10);

        LOG.info("selectPollEntities >> retrieved: " + pollDtoList.size());
        assertThat(pollDtoList).hasSize(3);

        assertThat(pollDtoList.get(0).getId()).isEqualTo("poll-01");
        assertThat(pollDtoList.get(0).getProposalDtoList()).hasSize(2);
        assertThat(pollDtoList.get(0).getProposalDtoList().get(0).getVoteDtoList()).hasSize(3);

        assertThat(pollDtoList.get(1).getId()).isEqualTo("poll-02");
        assertThat(pollDtoList.get(1).getProposalDtoList()).hasSize(0);

        assertThat(pollDtoList.get(2).getId()).isEqualTo("poll-03");
        assertThat(pollDtoList.get(2).getProposalDtoList()).hasSize(1);
    }
}