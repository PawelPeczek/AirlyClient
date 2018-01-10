package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.HTTPclient.AirlyAPIClient;
import edu.cs.agh.airly_client.HTTPclient.HTTPClientException;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.ProcessingException;

import java.io.IOException;

public class NearestSensorModel extends Model{
    @Override
    public void processData(ProgramInput input)
            throws IOException, InterruptedException, HTTPClientException, ProcessingException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        MapPoint data = AirlyAPI.getMeasurementsForMapPoint(input.getLatitude(), input.getLongitude());
        if(isObjectEmpty(data)) throw new ProcessingException("Server doesn't know the answer for given query.");
        notifyViews("SensorData", data);
        if(input.isHistory()) notifyViews("HistoryMode", true);
        else notifyViews("HistoryMode", false);
    }
}
