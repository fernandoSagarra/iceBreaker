
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.config;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author fernando
 */
public class MongoDB {
    
    private final Morphia morphia;
    private final Datastore datastore;
    private final MongoClient mongoClient;
    private final String mappedPackage;
    private final String databaseName;

    
    public MongoDB(Morphia morphia, MongoClient mongoClient, String mappedPackage, String databaseName) throws ClassNotFoundException{
        this.morphia = morphia;
        this.mongoClient = mongoClient;
        this.mappedPackage = mappedPackage;
        this.databaseName = databaseName;
        
        this.morphia.mapPackage(this.mappedPackage);
        
        this.datastore = this.morphia.createDatastore(this.mongoClient, this.databaseName);
        
        this.datastore.ensureIndexes();
        this.datastore.ensureCaps();
    }
    
    public Datastore getDatabase() {
        return datastore;
    }
    
}
