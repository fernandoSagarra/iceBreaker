
package com.apptouch.ws.icebreaker.businesslogic.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fernando
 */
public final class DateUtils {
    
    public static Date convertStringToDate(String format, String date) throws ParseException{
        Date result = null;
        if(format != null && !format.isEmpty() && date != null && !date.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            result = sdf.parse(date);    
        }
        return result;
    }
    
    public static String convertDateToString(String format, Date date){
        String result = "";
        if(format != null && !format.isEmpty() && date != null){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(date);   
        }
        return result;
    }
    
    public static String getCurrentDateInGivenFormat(String format){
        String result = "";
        Date date = new Date();        
        if(format != null && !format.isEmpty()){
              SimpleDateFormat sdf = new SimpleDateFormat(format);
              result = sdf.format(date);
        }
        
        return result;
    }
    
    
}
