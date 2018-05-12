/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.persistence;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.config.MongoDB;
import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.BaseEntity;
import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.UserBaseEntity;
import com.apptouch.ws.icebreaker.businesslogic.model.SubscriberModel;
import com.apptouch.ws.icebreaker.businesslogic.utils.exceptions.jerseyexceptions.MongoUpdateException;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 *
 * @author fernando
 */
public class MongoDBUserPersistence {
    
    private MongoDB mongoDB;
     
    public BaseEntity persist(BaseEntity entity) {
       this.mongoDB.getDatabase().save(entity);
       return entity;
    }
    public <E extends BaseEntity> long count(Class<E> clazz) { // Is better Class <? extends BaseEntity> ???
        if (clazz == null) {
            return 0;
        }
        return this.mongoDB.getDatabase().find(clazz).count();
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

        return this.mongoDB.getDatabase().find(clazz).field("_id").equal(mongoId).get();
    }
    public <E extends BaseEntity> E getEntityByStringAttribute(Class<E> clazz, final String attribute, 
                                                                                    final String field) {
        if ((clazz == null) || (attribute == null) || (attribute.isEmpty())) {
            return null;
        }

        return this.mongoDB.getDatabase().find(clazz).field(field).equal(attribute).get();
    }   
    public <E extends BaseEntity> E updateFieldById(Class<E> clazz, final String id,
            String fieldToUpdate, String value){
        if ((clazz == null) || fieldToUpdate.isEmpty()) {
            return null;
        }
         Query updateQuery = this.mongoDB.getDatabase().find(clazz).field("_id").equal(id);
         UpdateOperations<E> ops = this.mongoDB.getDatabase().createUpdateOperations(clazz).set(fieldToUpdate, value);
         this.mongoDB.getDatabase().update(updateQuery, ops);
         
        return this.mongoDB.getDatabase().find(clazz).field("_id").equal(id).get();
    }
    public UserBaseEntity saveUserEntity(UserBaseEntity user) {
        
        return (UserBaseEntity)persist(user);
    }   
    public <E extends UserBaseEntity> E getUserById(Object id, Class<E> clazz){
        return getEntityById(clazz, id);
    }
    public UserBaseEntity getUserByPassword(String password){
        return getEntityByStringAttribute(UserBaseEntity.class, password, "password");
    } 
    public <E extends UserBaseEntity> E getUserByUserNameAndEmail(Class<E> clazz, List<String> attributes, List<String> values){
        if ((clazz == null) || (attributes == null) || (attributes.isEmpty())) {
            return null;
        } 
        Query<E> query = mongoDB.getDatabase().createQuery(clazz);
        query.and(
                query.criteria(attributes.get(0)).equal(values.get(0)),
                query.criteria(attributes.get(1)).equal(values.get(1))
        );
        return query.get();
    }
    public <E extends UserBaseEntity> E getUserByTwoAttributes(Class<E> clazz, List<String> attributes, List<String> values){
        if ((clazz == null) || (attributes == null) || (attributes.isEmpty())) {
            return null;
        } 
        Query<E> query = mongoDB.getDatabase().createQuery(clazz);
        query.and(
                query.criteria(attributes.get(0)).equal(values.get(0)),
                query.criteria(attributes.get(1)).equal(values.get(1))
        );
        return query.get();
    }
    /*This method returns a SubscriberModel object corresponding to a given Id
 * a projection is performed so we only fetch favoritePlaces list
 * @param Object id: A given Id to search for
 * @return: A matching subscriber with a projection on favoritePlaces field
 */  
    public SubscriberModel getAllFavoritePlacesIdsForSubscriber(Object id){
        SubscriberModel result;
        ObjectId userId = null;
        if(id == null){
           throw new MongoUpdateException("Unknown subscriber to get favoritePlaces from");
        }
        if(id instanceof String){
            userId = new ObjectId((String)id);
        }else if(id instanceof ObjectId){
            userId = (ObjectId)id;
        }     
        Query<SubscriberModel> query = mongoDB.getDatabase().createQuery(SubscriberModel.class);
        result = query.field("_id")
                            .equal(userId)
                            .project("favoritePlaces", true)
                            .get();
        return result;
    }
/*This method returns a SubscriberModel object corresponding to a given Id
 * a projection is performed so we only fetch friends list
 * @param Object id: A given Id to search for
 * @return: A matching subscriber with a projection on friends field
 */    
    public SubscriberModel getAllFriendsIdsForSubscriber(Object id){
        SubscriberModel result;
        String userId = null;
        if(id == null){
           throw new MongoUpdateException("Unknown subscriber to get friends from");
        }
        if(id instanceof String){
            userId = (String)id;
        }else if(id instanceof ObjectId){
            userId = id.toString();
        }        
        Query<SubscriberModel> query = mongoDB.getDatabase().createQuery(SubscriberModel.class);
        result = query.field("_id")
                            .equal(userId)
                            .project("friends", true)
                            .get();
        return result;
    }
    public UserBaseEntity addNewFriend(Object id, SubscriberModel friend){
       if(friend == null){
           throw new MongoUpdateException("No friend found. Please add a friend");
       }
       if(id == null){
           throw new MongoUpdateException("Unknown subscriber to add a friend to");
       }
       String userId = "";
        if(id instanceof String){
            userId = (String)id;
        }else if(id instanceof ObjectId){
            userId = id.toString();
        }  
        if(userId.isEmpty()){
            throw new MongoUpdateException("Unknown subscriber to add a friend to");
        }
       Query<SubscriberModel> updateQuery = mongoDB.getDatabase().find(SubscriberModel.class);
       updateQuery.field("_id").equal(userId);
       UpdateOperations ops = mongoDB.getDatabase()
               .createUpdateOperations(SubscriberModel.class)
               .addToSet("friends",friend);
       mongoDB.getDatabase().update(updateQuery, ops,false);  
       return friend;
    }
    
    
    public boolean updateSubscriberProfileImageUrl(Object id, String url){
        String userId = "";
        if(id == null){
           throw new MongoUpdateException("Unknown subscriber to update profileUrl to");
        }
        if(id instanceof String){
            userId = (String)id;            
        }else if(id instanceof ObjectId){
            userId = id.toString();
        }  
        if(userId.isEmpty()){
            throw new MongoUpdateException("Unknown subscriber to update profileUrl to");
        }        
        return (updateFieldById(SubscriberModel.class, userId, "pictureUrl", url) instanceof BaseEntity);
    }
    public void setMongoDB(MongoDB mongoDB){
        this.mongoDB = mongoDB;
    }
    public MongoDB setMongoDB(){
        return this.mongoDB;
    }
    
}
