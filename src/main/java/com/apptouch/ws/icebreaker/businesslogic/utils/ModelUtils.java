
package com.apptouch.ws.icebreaker.businesslogic.utils;

import java.util.Map;

/**
 *
 * @author fernando
 */
public final class ModelUtils {
    
    public static String getProperty(Map data, String property){
        
        String result = "";
        if(data != null && property != null && !property.isEmpty()){
            result = (data.get(property) != null)?(String)data.get(property):"";
        }        
        return result;
    }
     public static <T> T getGenericProperty(Map data, String property){
        
        T result = null;
        
        if(data != null && property != null && !property.isEmpty()){
            result = (data.get(property) != null)?(T)data.get(property):null;
        }        
        return result;
    }
     
    
}
