package com.schibsted.onepunch.polldemo.infrastructure.controller.rest;

import com.schibsted.onepunch.polldemo.infrastructure.controller.rest.dto.CreatePollRequest;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.is;

public class RestPollControllerTest extends AbstractRestControllerTestCase {

    @Test
    @Sql({"/fixtures/db/truncates.sql", "/fixtures/db/inserts-03-polls.sql"})
    public void shouldGetAnExistingPoll() throws Exception {
        String givenPollId = "poll-01";
        GET("/poll/" + givenPollId).statusCode(HttpStatus.OK.value());
    }

    @Test
    @Sql({"/fixtures/db/truncates.sql"})
    public void shouldReturn404() throws Exception {
        String givenPollId = "invent";
        GET("/poll/" + givenPollId).statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @Sql({"/fixtures/db/truncates.sql"})
    public void shouldCreateAPoll() throws Exception {
        String givenSubject = "This is a new poll";
        CreatePollRequest createPollRequest = new CreatePollRequest();
        createPollRequest.setSubject(givenSubject);
        ValidatableResponse result = POST("/poll", createPollRequest);

        result.statusCode(HttpStatus.CREATED.value());
        result.body("subject", is(givenSubject));
    }

    @Test
    @Sql({"/fixtures/db/truncates.sql"})
    public void shouldNotCreateAnInvalidPoll() {
        String givenSubject = "";
        CreatePollRequest createPollRequest = new CreatePollRequest();
        createPollRequest.setSubject(givenSubject);
        POST("/poll", createPollRequest).statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    @Sql({"/fixtures/db/truncates.sql"})
    public void shouldNotPostAnInvalidObject() {
        POST("/poll", "invalid data").statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
