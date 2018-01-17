package edu.cs.agh.airly_client.Threading;

import edu.cs.agh.airly_client.RESTClient.AirlyAPIClient;

/**
 * Represents task to be executed in parallel.
 */
public abstract class AirlyTask {
    /**
     * APIObject to use REST API of AIRLY.
     */
    protected AirlyAPIClient APIObject;
    /**
     * SensorId requested by user.
     */
    protected Integer sensorId = null;
    /**
     * longitude requested by user.
     */
    protected Double longitude = null;
    /**
     * latitude requested by user.
     */
    protected Double latitude = null;


    public AirlyTask(AirlyAPIClient APIObject, Integer sensorId){
        this.APIObject = APIObject;
        this.sensorId = sensorId;
    }

    public AirlyTask(AirlyAPIClient APIObject, Double longitude, Double latitude){
        this.APIObject = APIObject;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
