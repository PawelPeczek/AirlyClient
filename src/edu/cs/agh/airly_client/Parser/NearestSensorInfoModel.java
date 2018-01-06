package edu.cs.agh.airly_client.Parser;

import edu.cs.agh.airly_client.HTTPclient.AirlyAPIClient;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.Model.Model;

import java.io.IOException;

public class NearestSensorInfoModel extends Model {
    @Override
    public void processData(ProgramInput input) throws IOException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        SingleSensor data = AirlyAPI.getMeasurementForNearestSensor(input.getLatitude(), input.getLongitude());
        notifyViews("SensorInfo", data);
    }
}
