
package com.apptouch.ws.icebreaker.businesslogic.database.mongodb.entities;

import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author fernando
 */

@Embedded
public class Location {
    private Double lat;
    private Double lon;

    public Double getLat() {
        return this.lat;
    }

    public void setLat(final Double lat) {
        this.lat = lat;
    }

    public Double getLong() {
        return this.lon;
    }

    public void setLong(final Double lon) {
        this.lon = lon;
    }
}
