package com.sqills.consumer.binder;

/**
 * Message Input Binder, fetch data from external system to internal
 */
public interface MessageInputBinder
{
    void receiveMessage();
}
