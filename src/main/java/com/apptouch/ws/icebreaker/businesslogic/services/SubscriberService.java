/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.services;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.UserBaseEntity;
import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.persistence.MongoDBUserPersistence;
import com.apptouch.ws.icebreaker.businesslogic.model.SubscriberModel;
import com.apptouch.ws.icebreaker.businesslogic.repositories.SubscriberRepository;
import com.apptouch.ws.icebreaker.businesslogic.utils.MapperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class SubscriberService implements SubscriberRepository{
    
    private MongoDBUserPersistence userPersistence;
    
    public MongoDBUserPersistence getUserPersistence() {
        return userPersistence;
    }

    public void setUserPersistence(MongoDBUserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    @Override
    public SubscriberModel getAllFavoritePlacesIdsForSubscriber(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubscriberModel getAllFriendsIdsForSubscriber(Object id) {
        return userPersistence.getAllFriendsIdsForSubscriber(id);
    }

    @Override
    public URI getURIById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserBaseEntity addNewSubscriber(String user) {
        UserBaseEntity result = null;
        try {            
            UserBaseEntity subscriber = MapperUtils.getMapper().readValue(user, SubscriberModel.class);
            result = userPersistence.saveUserEntity(subscriber);
        } catch (IOException ex) {
            Logger.getLogger(SubscriberService.class.getName()).log(Level.SEVERE, null, ex);
            //PLEASE CREATE A CLASS EXCEPTION TO FILL UP A MESSAGE
        } 
        return result;        
    }
 
    @Override
    public UserBaseEntity addNewfriendToSubscriber(Object id, String friendId) {
        SubscriberModel subscriber = userPersistence.getUserById(friendId, SubscriberModel.class);
        return (SubscriberModel)userPersistence.addNewFriend(id,subscriber);                
    }

    @Override
    public SubscriberModel getSubscriberById(Object id) {
        return userPersistence.getUserById(id, SubscriberModel.class);
    }

    @Override
    public SubscriberModel getSubscriberByUserNameAndEmail(List<String> attributes, List<String> values) {
        return userPersistence.getUserByUserNameAndEmail(SubscriberModel.class, attributes, values);
    }

    @Override
    public String getSubscriberByPassword(String password) {
        if(!password.isEmpty()){
            UserBaseEntity subscriber = userPersistence.getUserByPassword(password);
            try {      
               return MapperUtils.getMapper().writeValueAsString(subscriber);                
            } catch (JsonProcessingException ex) {
                Logger.getLogger(SubscriberService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return "No subscriber Found";//TODO: Implement this in a resource file
    }   

    @Override
    public UserBaseEntity getSubscriberByEmailAndPassword(List<String> attributes, List<String> values) {
        return userPersistence.getUserByTwoAttributes(SubscriberModel.class, attributes, values);
    }

    @Override
    public boolean updateSubscriberProfileImageUrl(Object id, String url) {
        return userPersistence.updateSubscriberProfileImageUrl(id, url);
    }
    
}
