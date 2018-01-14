package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.HTTPclient.AirlyAPIClient;
import edu.cs.agh.airly_client.HTTPclient.HTTPClientException;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.Parser.ProgramInput;

import java.io.IOException;

public class SpecSensorModel extends Model{
    @Override
    public void processData(ProgramInput input)
            throws InterruptedException, HTTPClientException, IOException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        MapPoint data = AirlyAPI.getMeasurementsFromSpecificSensor(input.getSensorId());
        if(isObjectEmpty(data)) notifyViews("EmptyObject", Boolean.TRUE);
        else notifyViews("EmptyObject", Boolean.FALSE);
        notifyViews("SensorData", data);
        if(input.isHistory()) notifyViews("HistoryMode", Boolean.TRUE);
        else notifyViews("HistoryMode", Boolean.FALSE);
        if(input.isUnsupportedSensDetails()) notifyViews("SensorDetailsMode", Boolean.TRUE);
        else notifyViews("SensorDetailsMode", Boolean.FALSE);
    }
}
