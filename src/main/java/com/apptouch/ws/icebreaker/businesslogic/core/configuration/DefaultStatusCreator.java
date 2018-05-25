/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.core.configuration;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.config.MongoDB;
import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.Status;
import com.apptouch.ws.icebreaker.businesslogic.utils.MapperUtils;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fernando
 */
public class DefaultStatusCreator {
    
    private MongoDB mongoDB;
    private boolean createDefaultStatus;
    private final String STATUS_COLLECTION = "Status";


    public MongoDB getMongoDB() {
        return mongoDB;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }


    public boolean isCreateDefaultStatus() {
        return createDefaultStatus;
    }

    public void setCreateDefaultStatus(boolean createDefaultStatus) {
        this.createDefaultStatus = createDefaultStatus;
    }
    
    public void init() throws Exception {
	  if(this.createDefaultStatus){
               Logger.getLogger(DefaultStatusCreator.class.getName()).log(Level.INFO, "Creando default status table");
              
               try{
                   
                   loadDefaulStatusFromFile();
            
               }catch(IOException e){
                   
                   Logger.getLogger(DefaultStatusCreator.class.getName()).log(Level.INFO, "Error{0}", e.getMessage());
            
               }
              
              
          }
    }
    
    private void loadDefaulStatusFromFile() throws IOException, ParseException{
        
        List<Status> states = new ArrayList<>();
        
        JSONParser parser = new JSONParser();        
        ClassLoader classLoader = getClass().getClassLoader();           
        
        Object json = parser.parse(new FileReader(classLoader.getResource("createDefaultStatus.json").getFile()));
        JSONObject jsonObject = (JSONObject) json;
        JSONArray defaultStatus = (JSONArray) jsonObject.get("defaultStatus");
        
        if (defaultStatus != null) {
            for (Object obj : defaultStatus) {
                JSONObject jsonStatus = (JSONObject) obj;
                Status current = MapperUtils.getMapper().readValue(jsonStatus.toString(), Status.class);
                if(current != null){
                    states.add(current);
                    //this.mongoDB.getDatabase().save(current);
                }
                
            }
            if(!states.isEmpty()){
                this.mongoDB.getDatabase().save(states);
                 Logger.getLogger(DefaultStatusCreator.class.getName()).log(Level.INFO, "Adicionando un status en la database");
            }
        }
        
    }
    
    
}
