
package com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions;

/**
 *
 * @author fernando
 */
public class MongoUpdateNullPointerException extends RuntimeException{
    /*This is the case when no data is given to be inserted as an Entity, so the service return null value*/
    public MongoUpdateNullPointerException(String message){
        super(message);
    }
    
}
