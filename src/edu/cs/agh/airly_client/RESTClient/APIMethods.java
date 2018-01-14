package edu.cs.agh.airly_client.RESTClient;

/**
 * Enum that describes API methods possible to use
 * in a program.
 */
public enum APIMethods {
    mapPoint("/v1/mapPoint/measurements"),
    nearestSensor("/v1/nearestSensor/measurements"),
    sensorMeasurements("/v1/sensor/measurements");

    public final String methodName;
    private APIMethods(String methodName){
        this.methodName = methodName;
    }
}
