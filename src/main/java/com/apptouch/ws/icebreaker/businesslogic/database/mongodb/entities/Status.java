/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author fernando
 */
@Entity
public class Status extends BaseEntity{
     
    private String title;
    private String type;
    @Embedded
    private ActionEntity action;
    
    public Status(){
        super();
    }
    
     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ActionEntity getAction() {
        return action;
    }

    public void setAction(ActionEntity action) {
        this.action = action;
    }
    
    
}
