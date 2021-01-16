package com.sqills.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Named( value = "simpleConsumer" )
@ApplicationScoped
public class SimpleConsumer implements MessageConsumer
{

    private static final Logger logger = LoggerFactory.getLogger(SimpleConsumer.class);

    @Inject
    ConnectionFactory connectionFactory;

    private String receiveQueue = "prices";

    @Override
    public void receiveMessage()
    {
        try
        {

            JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
            JMSConsumer consumer = context.createConsumer(context.createQueue(receiveQueue));
            while(true)
            {
                Message message = consumer.receive();
                if(message == null)
                    return;
                String messageBody = message.getBody(String.class);
                logger.info(messageBody);
            }
        }
        catch( JMSException e )
        {
            logger.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
