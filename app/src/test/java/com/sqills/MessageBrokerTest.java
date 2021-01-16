package com.sqills;

import com.sqills.broker.MessageBroker;
import com.sqills.simpleConsumer.SimpleMessageConsumer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Warning  please first run artemis as docker-compose up -d or in setup folder ./setup.sh
 * after artemis initialized and test it.
 *
 * @author mithat.konuk
 */
@QuarkusTest
public class MessageBrokerTest
{

    @Inject
    @Named( value = "sqillsMessageBroker" )
    MessageBroker messageBroker;

    @Inject
    @Named( value = "simpleMessageConsumer" )
    SimpleMessageConsumer consumer;

    @Test
    public void foo()
    {
        // when
        String message = "test";

        // get
        messageBroker.sendMessage(message);

        //then
        String messageBody = consumer.receiveMessage();

        Assertions.assertEquals(message, messageBody);
    }
}
