/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.core.configuration;

import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.mappers.DataNotFoundMongoExceptionMapper;
import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.mappers.MongoUpdateExceptionMapper;
import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.mappers.MongoUpdateNullPointerExceptionMapper;
import com.apptouch.ws.icebreaker.resources.MyResource;
import com.apptouch.ws.icebreaker.resources.SubscriberREST;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 *
 * @author fernando
 */
public class iceBreakerApp extends ResourceConfig{
    
    public iceBreakerApp() {
        register(RequestContextFilter.class);
        register(SubscriberREST.class);
        register(MyResource.class);
        register(DataNotFoundMongoExceptionMapper.class);
        register(MongoUpdateExceptionMapper.class);
        register(MongoUpdateNullPointerExceptionMapper.class);
    }
    
    
    
}
