package com.sqills.binder.artemis;

import com.sqills.binder.MessageBinder;
import com.sqills.binder.MessageBinderProvider;
import jdk.jfr.Name;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * provide configure binder here about artemis , or initiate another a new instance of binder or customize it
 *
 * @author mithat.konuk
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
