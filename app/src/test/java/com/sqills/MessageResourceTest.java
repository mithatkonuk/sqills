package com.sqills;

import com.sqills.simpleConsumer.SimpleMessageConsumer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class MessageResourceTest
{

    @Test
    public void send_message_to_endpoint_and_receive_it_from_external_system()
    {
        String message = "hello";
        given().contentType("text/plain").when().body(message).post("/sqills/broker/message").then()
                        .statusCode(200).body(is("{\"data\":true,\"error\":null}"));

    }
}
