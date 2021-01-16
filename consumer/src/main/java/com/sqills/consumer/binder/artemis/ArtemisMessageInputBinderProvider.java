package com.sqills.consumer.binder.artemis;

import com.sqills.consumer.binder.MessageInputBinder;
import com.sqills.consumer.binder.MessageInputBinderProvider;
import jdk.jfr.Name;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * We can configure binder here about artemis
 */
@Named( value = "artemisBinder" )
@Singleton
public class ArtemisMessageInputBinderProvider implements MessageInputBinderProvider
{
    @Inject
    @Name( value = "artemisInputBinder" )
    MessageInputBinder artemisInputBinder;

    @Override
    public MessageInputBinder bindMessageToInput()
    {
        return artemisInputBinder;
    }
}
