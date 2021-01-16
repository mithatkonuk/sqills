package com.sqills.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Session;

@Named( value = "sqillsMessageBroker" )
@ApplicationScoped
public class SqillsMessageBroker implements MessageBroker
{

    private static final Logger logger = LoggerFactory.getLogger(SqillsMessageBroker.class);

    @Inject
    ConnectionFactory connectionFactory;

    //@ConfigProperty( name = "" )
    String channel = "prices";
    
    @Override
    public void sendMessage( String message )
    {
        try
        {
            JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
            context.createProducer().send(context.createQueue(channel), message);
        }
        catch( JMSRuntimeException ex )
        {
            logger.error(ex.getLocalizedMessage());
        }

    }

    @Override
    public void receiveMessage( String message )
    {
        throw new UnsupportedOperationException("This operation is not  supported yet");
    }

}
