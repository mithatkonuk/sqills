package com.sqills.broker;

public interface MessageBroker
{
    void sendMessage(String message);

    void receiveMessage( String message );
}
