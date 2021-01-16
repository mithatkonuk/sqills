package com.sqills.resource;

import com.sqills.broker.MessageBroker;
import com.sqills.consumer.MessageConsumer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/sqills" )
public class SqillsMessageResource
{

    @Inject
    @Named( "sqillsMessageBroker" )
    MessageBroker sqillsMessageBroker;

    @Inject
    @Named( "simpleConsumer" )
    MessageConsumer simpleConsumer;

    /**
     * Message retrieved from external via HTTP request
     *
     * @param message string
     */
    @POST
    @Path( "message" )
    @Consumes( MediaType.TEXT_PLAIN )
    public void externalMessage( String message )
    {
        this.sqillsMessageBroker.sendMessage(message);
    }

    @GET
    @Path( "receive" )
    @Produces( MediaType.TEXT_PLAIN )
    public void receiveMessage()
    {
        this.simpleConsumer.receiveMessage();
    }
}