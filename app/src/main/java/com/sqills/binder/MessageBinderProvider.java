package com.sqills.binder;

/**
 * Message Binder Provider , duplicate way providing
 * bind to get data ( bint to output) , bind to sent data  ( bind to input)
 */
public interface MessageBinderProvider
{

    MessageBinder bindMessageToOutput();

    MessageBinder bindMessageToInput();
}
