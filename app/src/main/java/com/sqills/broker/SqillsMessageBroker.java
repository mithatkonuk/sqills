package com.sqills.broker;

import com.sqills.binder.MessageBinder;
import com.sqills.binder.MessageBinderProvider;
import com.sqills.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.jms.JMSRuntimeException;

/**
 * Message Broker
 */
@Named( value = "sqillsMessageBroker" )
@Singleton
public class SqillsMessageBroker implements MessageBroker
{

    private static final Logger logger = LoggerFactory.getLogger(SqillsMessageBroker.class);

    @Inject
    @Named( "artemisBinder" )
    MessageBinderProvider messageBinderProvider;

    private MessageBinder messageBinder;

    @PostConstruct
    public void init()
    {
        this.messageBinder = this.messageBinderProvider.bindMessageToOutput();
    }

    @Override
    public void sendMessage( final String message )
    {
        try
        {
            this.messageBinder.sendMessage(message);
        }
        catch( JMSRuntimeException ex )
        {
            logger.error(ExceptionUtils.stackTrace(ex));
        }

    }

    @Override
    public String receiveMessage( String message )
    {
        // just create input binder , and bind here
        throw new UnsupportedOperationException("Currently unsupported");
    }
}
