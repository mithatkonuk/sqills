package com.sqills.consumer.binder.artemis;

import com.sqills.consumer.binder.MessageInputBinder;
import com.sqills.consumer.utils.ExceptionUtils;
import io.quarkus.scheduler.Scheduled;
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
import java.util.concurrent.TimeUnit;

/**
 * forward message to another binder or external system
 */
@Named( value = "artemisInputBinder" )
@Singleton
public class ArtemisInputBinder implements MessageInputBinder
{
    private static final Logger logger = LoggerFactory.getLogger(ArtemisInputBinder.class);

    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty( name = "topic.consumer" )
    String topic;

    private JMSContext context;
    private JMSConsumer consumer;

    @PostConstruct
    public void init()
    {
        this.context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
        this.consumer = context.createConsumer(context.createQueue(topic));
    }

    @Scheduled( every = "2s",
                delayUnit = TimeUnit.SECONDS,
                delay = 2 )
    @Override
    public void receiveMessage()
    {
        try
        {
            Message message = consumer.receive();
            if(message == null)
                return;
            String messageBody = message.getBody(String.class);

            logger.info("Received message from broker: {} time : {}", messageBody, Instant.now());

        }
        catch( JMSException e )
        {
            logger.error(ExceptionUtils.stackTrace(e));
        }

    }

}
