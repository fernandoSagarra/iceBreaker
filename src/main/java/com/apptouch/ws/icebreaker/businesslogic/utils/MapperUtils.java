
package com.apptouch.ws.icebreaker.businesslogic.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Bill Pugh Singleton Implementation. To check
 * @author fernando
 */
public class MapperUtils {
    
    private MapperUtils(){}
    
    private static class MapperUtilsHelper{
        private static final ObjectMapper INSTANCE = new ObjectMapper();        
    }
    
    public static ObjectMapper getMapper(){   
        if(MapperUtilsHelper.INSTANCE != null){
           // MapperUtilsHelper.INSTANCE.configure(Serialization, true)
        }
        return MapperUtilsHelper.INSTANCE;
    }
    
}
