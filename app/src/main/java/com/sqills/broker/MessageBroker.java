package com.sqills.broker;

public interface MessageBroker
{
    void sendMessage( final String message );

    String  receiveMessage( final String message );
}
