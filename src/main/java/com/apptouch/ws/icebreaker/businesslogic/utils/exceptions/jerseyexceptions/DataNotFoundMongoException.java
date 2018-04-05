
package com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions;

/**
 *
 * @author fernando
 */
public class DataNotFoundMongoException extends RuntimeException{
     public DataNotFoundMongoException(String message){
        super(message);
    }  
}
