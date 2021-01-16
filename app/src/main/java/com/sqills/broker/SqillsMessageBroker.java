package com.sqills.broker;

import com.sqills.utils.ExceptionUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.Session;

@Named( value = "sqillsMessageBroker" )
@Singleton
public class SqillsMessageBroker implements MessageBroker
{

    private static final Logger logger = LoggerFactory.getLogger(SqillsMessageBroker.class);

    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty( name = "topic.broker" )
    String topic;

    private JMSContext context;
    private JMSProducer producer;

    @PostConstruct
    public void init()
    {
        this.context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
        this.producer = context.createProducer();
    }

    @Override
    public void sendMessage( final String message )
    {
        try
        {

            producer.send(context.createQueue(topic), message);
        }
        catch( JMSRuntimeException ex )
        {
            logger.error(ExceptionUtils.stackTrace(ex));
        }

    }

    @Override
    public void receiveMessage( final String message )
    {
        throw new UnsupportedOperationException("This operation is not  supported yet");
    }

}
