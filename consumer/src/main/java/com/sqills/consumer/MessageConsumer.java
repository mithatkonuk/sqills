package com.sqills.consumer;

import com.sqills.consumer.binder.MessageInputBinder;
import com.sqills.consumer.binder.MessageInputBinderProvider;
import com.sqills.consumer.receiver.MessageReceiver;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Message Consumer which bind artemis input binder to get message
 */
@Singleton
public class MessageConsumer implements MessageReceiver
{
    @Inject
    @Named( value = "artemisBinder" )
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
