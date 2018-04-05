
package com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.mappers;

import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.MongoUpdateNullPointerException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author fernando
 */
@Provider
public class MongoUpdateNullPointerExceptionMapper implements ExceptionMapper<MongoUpdateNullPointerException> {

    @Override
    public Response toResponse(MongoUpdateNullPointerException e) {
        //ErrorMessage errorMessage = new ErrorMessage("Document is empty. Please, send data to database", 403);
        return Response.status(Response.Status.FORBIDDEN) 
                //.entity(errorMessage.getErrorMessage())
                .entity(e.getMessage())
                .build();
    }
    
}
