package com.schibsted.onepunch.polldemo.infrastructure.configuration;

import com.schibsted.onepunch.polldemo.domain.poll.PollFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfiguration {

    @Bean
    public PollFactory pollFactory() {
        return new PollFactory();
    }
}
