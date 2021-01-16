package com.sqills.binder;

/**
 * Message Binder is duplicate binder
 * message received and also send to another system
 * , binder etc.
 */
public interface MessageBinder
{
    void sendMessage( String message );

    String receiverMessage();
}
