
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities;

import com.apptouch.ws.icebreaker.businesslogic.database.mongodb.types.Gender;
import java.util.Locale;
import org.mongodb.morphia.annotations.Entity;

/**
 *
 * @author fernando
 */
@Entity
public class UserBaseEntity extends BaseEntity{
    protected String email; 
    protected String name; 
    protected String lastName; 
    protected String userName;     
    protected String password; // WE ARE ASSUMING ALL OF ARE CLIENTS NEED TO SIGN UP
    protected Gender gender; 
    protected int age; 
    protected Locale locale; 
    
    public UserBaseEntity(){
        super();
    }
   
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        if (gender == null) {
            return Gender.UNSPECIFIED;
        } else {
            return gender;
        }
    }

    public void setGender(Gender gender) {
        if(gender == null){
             this.gender = Gender.UNSPECIFIED;
        }else{
             this.gender = gender;
        }
       
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
