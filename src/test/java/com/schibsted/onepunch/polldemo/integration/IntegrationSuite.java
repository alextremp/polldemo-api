package com.schibsted.onepunch.polldemo.integration;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import com.schibsted.onepunch.polldemo.infrastructure.controller.rest.RestPollControllerTest;
import com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc.mapper.PollMapperTest;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RestPollControllerTest.class, PollMapperTest.class})
public class IntegrationSuite {

    @ClassRule
    public static DockerComposeRule docker = DockerComposeRule.builder()
            .file("docker-compose.yml")
            .waitingForService("db", HealthChecks.toHaveAllPortsOpen())
            .saveLogsTo("target/docker-logs")
            .build();

}
