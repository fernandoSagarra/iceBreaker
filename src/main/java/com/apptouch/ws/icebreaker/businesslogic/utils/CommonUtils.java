
package com.apptouch.ws.icebreaker.businesslogic.utils;

import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.URIException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author fernando
 */
public class CommonUtils {
    
     /**
     * Convert a string into an URI.
     * Taken from pac4j open source project
     *
     * @param s the string
     * @return the URI
     */
    public static URI asURI(final String s) {
        try {
            return new URI(s);
        } catch (final URISyntaxException e) {
            
            throw new URIException("Cannot make an URI from: " + s, e);
        }
    }
    
    public static String getImageLocation(){
        return "/home/fernando/NetBeansProjects/Images/";
    }
    
    public static void saveFile(InputStream uploadedInputStream, String serverLocation) throws IOException{
        OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
    }
    public static void createFolderIfNotExists(String dirName) throws SecurityException {
        File theDir = new File(dirName);
        if (!theDir.exists()) {
            theDir.mkdir();
        }
    }

}
