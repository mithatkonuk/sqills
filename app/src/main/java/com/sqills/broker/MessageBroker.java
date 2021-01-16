package com.sqills.broker;

/**
 * Provide Message Broker to send and receive message via binders
 */
public interface MessageBroker
{
    void sendMessage( final String message );

    String receiveMessage( final String message );
}
