/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.utils.exceptions;

/**
 *
 * @author fernando
 */
public class URIException extends RuntimeException{
    
    public URIException(final String message) {
        super(message);
    }
    public URIException(final String message, final Throwable t) {
        super(message, t);
    }
}
