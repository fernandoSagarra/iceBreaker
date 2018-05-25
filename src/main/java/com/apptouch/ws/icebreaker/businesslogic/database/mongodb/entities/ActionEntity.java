
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities;

import java.util.List;
import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author fernando
 */
@Embedded
public class ActionEntity {
    
    private String parent;    
    private int level;
    private List<String> access;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<String> getAccess() {
        return access;
    }

    public void setAccess(List<String> access) {
        this.access = access;
    }
      
    
    /*public ActionEntity() {
        super();
    }*/
    
    
}
