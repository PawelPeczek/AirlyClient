package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.HTTPclient.AirlyAPIClient;
import edu.cs.agh.airly_client.HTTPclient.HTTPClientException;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.Parser.ProgramInput;

import java.io.IOException;

public class NearestSensorModel extends Model{
    @Override
    public void processData(ProgramInput input)
            throws IOException, InterruptedException, HTTPClientException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        MapPoint data = AirlyAPI.getMeasurementsForMapPoint(input.getLatitude(), input.getLongitude());
        if(isObjectEmpty(data)) notifyViews("EmptyObject", Boolean.TRUE);
        else notifyViews("EmptyObject", Boolean.FALSE);
        notifyViews("SensorData", data);
        if(input.isHistory()) notifyViews("HistoryMode", true);
        else notifyViews("HistoryMode", false);
    }
}
