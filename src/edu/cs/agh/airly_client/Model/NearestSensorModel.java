package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.HTTPclient.AirlyAPIClient;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.Parser.ProgramInput;

import java.io.IOException;

public class NearestSensorModel extends Model{
    @Override
    public void processData(ProgramInput input) throws IOException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        MapPoint data = AirlyAPI.getMeasurementsForMapPoint(input.getLatitude(), input.getLongitude());
        notifyViews("SensorData", data);
    }
}
