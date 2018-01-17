package edu.cs.agh.airly_client.Threading;

import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.RESTClient.AirlyAPIClient;

import java.util.concurrent.Callable;

public class SinglePointThread extends AirlyThread implements Callable<SingleSensor> {
    public SinglePointThread(AirlyAPIClient APIObject, Integer sensorId) {
        super(APIObject, sensorId);
    }

    public SinglePointThread(AirlyAPIClient APIObject, Double longitude, Double latitude) {
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
