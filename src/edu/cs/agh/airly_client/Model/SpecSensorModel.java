package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.HTTPclient.AirlyAPIClient;
import edu.cs.agh.airly_client.HTTPclient.HTTPClientException;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Parser.RunningMode;
import edu.cs.agh.airly_client.ProcessingException;

import java.io.IOException;

public class SpecSensorModel extends Model{
    @Override
    public void processData(ProgramInput input)
            throws InterruptedException, HTTPClientException, IOException, ProcessingException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        MapPoint data = AirlyAPI.getMeasurementsFromSpecificSensor(input.getSensorId());
        if(isObjectEmpty(data)) throw new ProcessingException("Server doesn't know the answer for given query.");
        notifyViews("SensorData", data);
        if(input.isHistory()) notifyViews("HistoryMode", Boolean.TRUE);
        else notifyViews("HistoryMode", Boolean.FALSE);
        if(input.isUnsupportedSensDetails()) notifyViews("SensorDetailsMode", Boolean.TRUE);
        else notifyViews("SensorDetailsMode", Boolean.FALSE);
    }
}
