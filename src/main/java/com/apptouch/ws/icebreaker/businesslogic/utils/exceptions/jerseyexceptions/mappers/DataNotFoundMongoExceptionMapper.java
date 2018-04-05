
package com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.mappers;

import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.ErrorMessage;
import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.DataNotFoundMongoException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author fernando
 */
@Provider
public class DataNotFoundMongoExceptionMapper implements ExceptionMapper<DataNotFoundMongoException>{

    @Override
    public Response toResponse(DataNotFoundMongoException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "Document not found");
        return Response.status(Response.Status.NOT_FOUND) 
                .entity(errorMessage)
                .build();
    }
    
}
