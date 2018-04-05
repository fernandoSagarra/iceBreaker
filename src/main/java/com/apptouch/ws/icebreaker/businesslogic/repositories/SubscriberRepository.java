/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.repositories;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.UserBaseEntity;
import java.net.URI;
import java.util.List;

/**
 *
 * @author fernando
 */
public interface SubscriberRepository {
    
    public UserBaseEntity getSubscriberById(Object id); 
    /*Combination has to be unique. We still need to check how to ensure this in mongodb/morphia*/
    public UserBaseEntity getSubscriberByUserNameAndEmail(List<String> attributes, List<String> values); 
    public UserBaseEntity getSubscriberByEmailAndPassword(List<String> attributes, List<String> values); 
    public UserBaseEntity getAllFavoritePlacesIdsForSubscriber(Object id); 
    public UserBaseEntity getAllFriendsIdsForSubscriber(Object id); 
    public URI getURIById(String id);
    public UserBaseEntity addNewSubscriber(String user);
    public UserBaseEntity addNewfriendToSubscriber(Object id, String user);
    public String getSubscriberByPassword(String password);
    public boolean updateSubscriberProfileImageUrl(Object id, String url);
    
}
