package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.HTTPclient.AirlyAPIClient;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.Model.Model;
import edu.cs.agh.airly_client.Parser.ProgramInput;

import java.io.IOException;

public class NearestSensorInfoModel extends Model {
    @Override
    public void processData(ProgramInput input) throws IOException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        SingleSensor data = AirlyAPI.getMeasurementForNearestSensor(input.getLatitude(), input.getLongitude());
        notifyViews("SensorInfo", data);
        if(input.isHistory()) notifyViews("HistoryMode", true);
        else notifyViews("HistoryMode", false);
    }
}
