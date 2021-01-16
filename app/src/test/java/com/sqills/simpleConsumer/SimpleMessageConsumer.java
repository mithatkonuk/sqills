package com.sqills.simpleConsumer;

import com.sqills.utils.ExceptionUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.time.Instant;

/**
 * We can also make consumer ,and broker as package so any project can use
 * this demostration is not considering
 */
@Singleton
@Named( value = "simpleMessageConsumer" )
public class SimpleMessageConsumer
{
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageConsumer.class);

    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty( name = "topic.broker" )
    String topic;

    private JMSContext context;
    private JMSConsumer consumer;

    @PostConstruct
    public void init()
    {
        this.context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
        this.consumer = context.createConsumer(context.createQueue(topic));
    }

    public String receiveMessage()
    {
        try
        {
            Message message = consumer.receive();
            if(message == null)
                return null;
            String messageBody = message.getBody(String.class);

            logger.info("Received message from broker: {} time : {}", messageBody, Instant.now());
            return messageBody;
        }
        catch( JMSException e )
        {
            logger.error(ExceptionUtils.stackTrace(e));
        }

        return null;
    }
}
