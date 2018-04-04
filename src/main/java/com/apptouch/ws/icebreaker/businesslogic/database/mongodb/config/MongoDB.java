
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.config;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.BaseEntity;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author fernando
 */
public class MongoDB {
    
    /*This values should eventually be injected through spring*/
    public static final String DB_HOST = "127.0.0.1";
    public static final int DB_PORT = 27017;
    public static final String DB_NAME = "icebreaker";

    private static final MongoDB INSTANCE = new MongoDB();
    private final Datastore datastore;

    private MongoDB() {
        MongoClientOptions mongoOptions = MongoClientOptions.builder()
                .socketTimeout(60000)
                .connectTimeout(15000)
                .maxConnectionIdleTime(600000)
                .readPreference(ReadPreference.primaryPreferred())
                .writeConcern(WriteConcern.ACKNOWLEDGED)
                .build();
        MongoClient mongoClient;
        mongoClient = new MongoClient(new ServerAddress(DB_HOST, DB_PORT), mongoOptions);

        datastore = new Morphia().mapPackage(BaseEntity.class.getPackage().getName())
                .createDatastore(mongoClient, DB_NAME);
        datastore.ensureIndexes();
        datastore.ensureCaps();

    }

    public static MongoDB instance() {
        return INSTANCE;
    }

    // Creating the mongo connection is expensive - (re)use a singleton for performance reasons.
    // Both the underlying Java driver and Datastore are thread safe.
    public Datastore getDatabase() {
        return datastore;
    }
    
}
