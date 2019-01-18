package com.schibsted.onepunch.polldemo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schibsted.onepunch.polldemo.PollDemoApplication;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;


@SpringBootTest(classes = PollDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractRestControllerTestCase extends AbstractIntegrationTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestControllerTestCase.class);

    @Value("${local.server.port}")
    protected Integer port;

    private Function<String, String> address = relativePath -> "http://localhost:" + port + relativePath;

    protected ValidatableResponse GET(String relativePath) {
        ValidatableResponse validatableResponse = RestAssured.get(address.apply(relativePath)).then();
        if (LOG.isDebugEnabled()) {
            validatableResponse.extract().response().prettyPrint();
        }
        return validatableResponse;
    }

    protected ValidatableResponse POST(String relativePath, Object data) {
        ValidatableResponse validatableResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(data)
                .when().post(address.apply(relativePath)).then();
        if (LOG.isDebugEnabled()) {
            validatableResponse.extract().response().prettyPrint();
        }
        return validatableResponse;
    }

}
