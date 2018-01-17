package edu.cs.agh.airly_client.Threading;

import edu.cs.agh.airly_client.RESTClient.AirlyAPIClient;

public abstract class AirlyThread {
    protected AirlyAPIClient APIObject;
    protected Integer sensorId = null;
    protected Double longitude = null;
    protected Double latitude = null;


    public AirlyThread(AirlyAPIClient APIObject, Integer sensorId){
        this.APIObject = APIObject;
        this.sensorId = sensorId;
    }

    public AirlyThread(AirlyAPIClient APIObject, Double longitude, Double latitude){
        this.APIObject = APIObject;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
