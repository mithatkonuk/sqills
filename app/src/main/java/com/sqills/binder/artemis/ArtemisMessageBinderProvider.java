package com.sqills.binder.artemis;

import com.sqills.binder.MessageBinder;
import com.sqills.binder.MessageBinderProvider;
import jdk.jfr.Name;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * We can configure binder here about artemis
 */
@Named( value = "artemisBinder" )
@Singleton
public class ArtemisMessageBinderProvider implements MessageBinderProvider
{
    @Inject
    @Name( value = "artemisOutputBinder" )
    MessageBinder artemisOutPutBinder;

    @Override
    public MessageBinder bindMessageToOutput()
    {
        return artemisOutPutBinder;
    }

    @Override
    public MessageBinder bindMessageToInput()
    {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
