
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities;

import java.util.Date;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

/**
 *
 * @author fernando
 */
public abstract class BaseEntity {
    
    @Id
    protected String id = new ObjectId().toString();
    protected Date creationDate;
    protected Date lastChange;      
    /**
     * No getters and setters required, the version is handled internally.
     */
    @Version
    private long version;

    public BaseEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastChange() {
        return lastChange;
    }   

    @PrePersist
    public void prePersist() {
        this.creationDate = (creationDate == null) ? new Date() : creationDate;
        this.lastChange = (lastChange == null) ? creationDate : new Date();
    }
    
}
