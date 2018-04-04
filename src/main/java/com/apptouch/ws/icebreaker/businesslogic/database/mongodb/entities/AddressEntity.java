
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities;

import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author fernando
 */
@Embedded
public class AddressEntity {
    
    protected String street;    
    protected String zip;
    protected String city;
    protected String country;    
    
    public AddressEntity() {
        super();
    }
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }   
    
}
