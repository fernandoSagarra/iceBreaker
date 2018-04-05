/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.mappers;

import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.ErrorMessage;
import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.MongoUpdateException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author fernando
 */
@Provider
public class MongoUpdateExceptionMapper implements ExceptionMapper<MongoUpdateException>{
    /*We should be in this case scenario when a not valid document is intended to be inserted in database*/
    @Override
    public Response toResponse(MongoUpdateException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 304, "Not valid document");
        return Response.status(Response.Status.FORBIDDEN) 
                .entity(errorMessage)
                .build();
    }
    
}
