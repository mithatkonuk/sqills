package com.sqills.consumer;

import com.sqills.consumer.binder.MessageInputBinder;
import com.sqills.consumer.binder.MessageInputBinderProvider;
import com.sqills.consumer.receiver.MessageReceiver;
import jdk.jfr.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MessageConsumer implements MessageReceiver
{

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Inject
    @Name( value = "artemisBinder" )
    MessageInputBinderProvider messageInputBinderProvider;

    MessageInputBinder messageInputBinder;

    @PostConstruct
    public void init()
    {
        this.messageInputBinder = messageInputBinderProvider.bindMessageToInput();
    }

    @Override
    public void receiveMessage()
    {
        this.messageInputBinder.receiveMessage();
    }
}
