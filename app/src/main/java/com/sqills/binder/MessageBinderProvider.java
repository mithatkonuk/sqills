package com.sqills.binder;

public interface MessageBinderProvider
{

    MessageBinder bindMessageToOutput();
    MessageBinder bindMessageToInput();
}
