package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.HTTPclient.AirlyAPIClient;
import edu.cs.agh.airly_client.HTTPclient.HTTPClientException;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.ProcessingException;

import java.io.IOException;

public class NearestSensorInfoModel extends Model {
    @Override
    public void processData(ProgramInput input)
            throws IOException, InterruptedException, HTTPClientException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        SingleSensor data = AirlyAPI.getMeasurementForNearestSensor(input.getLatitude(), input.getLongitude());
        if(isObjectEmpty(data)) notifyViews("EmptyObject", Boolean.TRUE);
        else notifyViews("EmptyObject", Boolean.FALSE);
        notifyViews("SensorInfo", data);
        if(input.isHistory()) notifyViews("HistoryMode", Boolean.TRUE);
        else notifyViews("HistoryMode",  Boolean.FALSE);
    }
}
