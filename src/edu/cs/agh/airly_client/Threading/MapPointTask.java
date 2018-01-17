package edu.cs.agh.airly_client.Threading;

import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.RESTClient.AirlyAPIClient;

import java.util.concurrent.Callable;

public class MapPointThread extends AirlyThread implements Callable<MapPoint> {

    public MapPointThread(AirlyAPIClient APIObject, Integer sensorId) {
        super(APIObject, sensorId);
    }

    public MapPointThread(AirlyAPIClient APIObject, Double latitude, Double longitude) {
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
