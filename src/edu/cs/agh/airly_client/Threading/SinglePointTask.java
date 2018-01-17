package edu.cs.agh.airly_client.Threading;

import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.RESTClient.AirlyAPIClient;

import java.util.concurrent.Callable;

/**
 * Class represents an object implements Callable that
 * is able to run task in parallel.
 */
public class SinglePointTask extends AirlyTask implements Callable<SingleSensor> {
    public SinglePointTask(AirlyAPIClient APIObject, Integer sensorId) {
        super(APIObject, sensorId);
    }

    public SinglePointTask(AirlyAPIClient APIObject, Double longitude, Double latitude) {
        super(APIObject, longitude, latitude);
    }

    @Override
    public SingleSensor call() throws Exception {
        SingleSensor result;
        if(sensorId == null)
            result = APIObject.getInfoABoutSingleSensor(latitude, longitude);
        else
            result = APIObject.getInfoABoutSingleSensor(sensorId);
        return result;
    }
}
