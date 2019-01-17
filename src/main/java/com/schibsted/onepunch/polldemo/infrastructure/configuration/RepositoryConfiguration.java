package com.schibsted.onepunch.polldemo.infrastructure.configuration;

import com.schibsted.onepunch.polldemo.domain.poll.PollFactory;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.JdbcPollRepository;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper.PollMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public JdbcPollRepository jdbcPollRepository(PollMapper pollMapper, PollFactory pollFactory) {
        return new JdbcPollRepository(pollMapper, pollFactory);
    }
}
