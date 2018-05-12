
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.config;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.BaseEntity;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author fernando
 */
public class MongoDB {
    
    private Morphia morphia;
    private Datastore datastore;
    private MongoClient mongoClient;
    private String mappedPackage;
    private String databaseName;

    
    public MongoDB(Morphia morphia, MongoClient mongoClient, String mappedPackage, String databaseName) throws ClassNotFoundException{
        this.morphia = morphia;
        this.mongoClient = mongoClient;
        this.mappedPackage = mappedPackage;
        this.databaseName = databaseName;
        
        this.morphia.mapPackage(this.mappedPackage);
        
        this.datastore = this.morphia.createDatastore(mongoClient, databaseName);
        
        this.datastore.ensureIndexes();
        this.datastore.ensureCaps();
    }
    
    public Datastore getDatabase() {
        return datastore;
    }
    
}
