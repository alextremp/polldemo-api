package com.schibsted.onepunch.polldemo.integration;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
public abstract class AbstractIntegrationTestCase {

    @ClassRule
    public static DockerComposeRule docker = DockerComposeRule.builder()
            .file("docker-compose.yml")
            .waitingForService("db", HealthChecks.toHaveAllPortsOpen())
            .saveLogsTo("target/docker-logs")
            .build();
}
