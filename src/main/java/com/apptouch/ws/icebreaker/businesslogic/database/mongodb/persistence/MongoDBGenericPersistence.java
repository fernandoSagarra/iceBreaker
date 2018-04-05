/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.persistence;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.config.MongoDB;
import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.BaseEntity;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 *
 * @author fernando
 */
public class MongoDBGenericPersistence {
    
    protected final Datastore mongoDatastore;
    
    public MongoDBGenericPersistence(){
        mongoDatastore = MongoDB.instance().getDatabase();
    }
    
    public BaseEntity persist(BaseEntity entity) {
       mongoDatastore.save(entity);
       return entity;
    }
    public <E extends BaseEntity> long count(Class<E> clazz) { // Is better Class <? extends BaseEntity> ???
        if (clazz == null) {
            return 0;
        }
        return mongoDatastore.find(clazz).count();
    }
    public <E extends BaseEntity> E getEntityById(Class<E> clazz, final Object id) {
        if ((clazz == null) || (id == null)) {
            return null;
        }
        String mongoId = null;
        if(id instanceof String){
            mongoId = (String)id;            
        }else if(id instanceof ObjectId){
            mongoId = id.toString();
        }  

        return mongoDatastore.find(clazz).field("_id").equal(mongoId).get();
    }
    public <E extends BaseEntity> E getEntityByStringAttribute(Class<E> clazz, final String attribute, 
                                                                                    final String field) {
        if ((clazz == null) || (attribute == null) || (attribute.isEmpty())) {
            return null;
        }

        return mongoDatastore.find(clazz).field(field).equal(attribute).get();
    }   
    public <E extends BaseEntity> E updateFieldById(Class<E> clazz, final String id,
            String fieldToUpdate, String value){
        if ((clazz == null) || fieldToUpdate.isEmpty()) {
            return null;
        }
         Query updateQuery = mongoDatastore.find(clazz).field("_id").equal(id);
         UpdateOperations<E> ops = mongoDatastore.createUpdateOperations(clazz).set(fieldToUpdate, value);
         mongoDatastore.update(updateQuery, ops);
         
        return mongoDatastore.find(clazz).field("_id").equal(id).get();
    }
}
