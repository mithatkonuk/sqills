package com.sqills.consumer;

import com.sqills.consumer.receiver.MessageReceiver;
import com.sqills.consumer.utils.ExceptionUtils;
import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Singleton
public class MessageConsumer implements MessageReceiver
{

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty( name = "sqills.consumer.messaging.topic" )
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

            logger.info("Received message from broker: {} {}", messageBody, Instant.now());

        }
        catch( JMSException e )
        {
            logger.error(ExceptionUtils.stackTrace(e));
            throw new RuntimeException(e);
        }
    }
}
