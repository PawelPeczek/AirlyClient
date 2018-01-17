package edu.cs.agh.airly_client.Threading;

import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.RESTClient.AirlyAPIClient;

import java.util.concurrent.Callable;

/**
 * Class represents an object implements Callable that
 * is able to run task in parallel.
 */
public class MapPointTask extends AirlyTask implements Callable<MapPoint> {

    public MapPointTask(AirlyAPIClient APIObject, Integer sensorId) {
        super(APIObject, sensorId);
    }

    public MapPointTask(AirlyAPIClient APIObject, Double latitude, Double longitude) {
        super(APIObject, longitude, latitude);
    }

    @Override
    public MapPoint call() throws Exception {
        MapPoint result;
        if(sensorId == null)
            result = APIObject.getMeasurementsForMapPoint(latitude, longitude);
        else
            result = APIObject.getMeasurementsFromSpecificSensor(sensorId);
        return result;
    }
}
