package edu.cs.agh.airly_client.JSON;

/**
 * POJO Class that represents basic SingleSensor data
 * which are possible to receive from server.
 */
public class SingleSensor extends BasicMeasurement {
    private Integer id;

    private Double distance;

    private Location location;

    private Address address;

    private String vendor;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", location=" + location +
                ", address=" + address +
                ", vendor='" + vendor + '\'' +
                ", name='" + name + '\'' +
                ", airQualityIndex=" + airQualityIndex +
                ", pollutionLevel=" + pollutionLevel +
                ", measurementTime='" + measurementTime + '\'' +
                ", pm10=" + pm10 +
                ", pm25=" + pm25 +
                "]";
    }

    public boolean isFulfilled(){
        return (id != null && name != null && vendor != null &&
                super.isFulfilled());
    }
}
