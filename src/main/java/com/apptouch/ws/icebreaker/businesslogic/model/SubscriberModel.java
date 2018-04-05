/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.model;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities.UserBaseEntity;
import java.net.URI;
import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author fernando
 */
@Entity
public class SubscriberModel extends UserBaseEntity{  
    
    private String pictureUrl; 
    private URI pictureURI; 
    private String profileUrl;   
    private URI profileURI;     
    @Reference(lazy=true)
    private List<SubscriberModel> friends; 
    
    public SubscriberModel(){
        super();
    }
    
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }    

    
    public URI getPictureURI() {
        return pictureURI;
    }

    public void setPictureURI(URI pictureURI) {
        this.pictureURI = pictureURI;
    }

    public URI getProfileURI() {
        return profileURI;
    }

    public void setProfileURI(URI profileURI) {
        this.profileURI = profileURI;
    }

    public List<SubscriberModel> getFriends() {
        return friends;
    }

    public void setFriends(List<SubscriberModel> friends) {
        this.friends = friends;
    }
    
    
}
