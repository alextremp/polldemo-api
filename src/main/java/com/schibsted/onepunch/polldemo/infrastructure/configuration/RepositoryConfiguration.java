package com.schibsted.onepunch.polldemo.infrastructure.configuration;

import com.schibsted.onepunch.polldemo.domain.poll.PollFactory;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.JdbcPollRepository;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper.PollMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }

    @Bean
    public JdbcPollRepository jdbcPollRepository(TransactionTemplate transactionTemplate, PollMapper pollMapper, PollFactory pollFactory) {
        return new JdbcPollRepository(transactionTemplate, pollMapper, pollFactory);
    }
}
