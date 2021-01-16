package com.sqills.broker;

public interface MessageBroker
{
    void sendMessage( final String message );

    void receiveMessage( final String message );
}
