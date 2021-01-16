package com.sqills.resource;

import com.sqills.broker.MessageBroker;
import com.sqills.resource.response.SqillsResponse;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
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

    /**
     * Message retrieved from external via HTTP request
     *
     * @param message string
     */
    @POST
    @Path( "/broker/message" )
    @Consumes( MediaType.TEXT_PLAIN )
    @Produces( MediaType.APPLICATION_JSON )
    public SqillsResponse receiveMessage( String message )
    {
        this.sqillsMessageBroker.sendMessage(message);
        return new SqillsResponse(true);
    }

}