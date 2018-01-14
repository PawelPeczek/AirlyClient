package edu.cs.agh.airly_client.JSON;

/**
 * POJO Class that represents location data
 * which are possible to receive from server.
 */
public class Location {
    private String longitude;

    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
