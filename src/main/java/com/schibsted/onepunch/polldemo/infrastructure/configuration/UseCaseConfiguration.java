package com.schibsted.onepunch.polldemo.infrastructure.configuration;

import com.schibsted.onepunch.polldemo.application.poll.CreatePollUseCase;
import com.schibsted.onepunch.polldemo.application.poll.GetPollUseCase;
import com.schibsted.onepunch.polldemo.domain.poll.PollFactory;
import com.schibsted.onepunch.polldemo.domain.poll.PollRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CreatePollUseCase createPollUseCase(PollFactory pollFactory, PollRepository pollRepository) {
        return new CreatePollUseCase(pollFactory, pollRepository);
    }

    @Bean
    public GetPollUseCase getPollUseCase(PollRepository pollRepository) {
        return new GetPollUseCase(pollRepository);
    }
}
