package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.RESTClient.AirlyAPIClient;
import edu.cs.agh.airly_client.RESTClient.RESTClientException;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.Parser.ProgramInput;

import java.io.IOException;

/**
 *  Model responsible for handing with nearestMeasurements running mode.
 */
public class NearestSensorModel extends Model{
    /**
     * Implementation of abstract method from Model class.
     *
     * @param input Parsed input from command-line.
     * @throws IOException when error with server connection occurs.
     * @throws InterruptedException when somewhat process is killed while
     * trying to reconnect to server.
     * @throws RESTClientException when inner RESTClient error occurs.
     */
    @Override
    public void processData(ProgramInput input)
            throws IOException, InterruptedException, RESTClientException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        MapPoint data = AirlyAPI.getMeasurementsForMapPoint(input.getLatitude(), input.getLongitude());
        if(isObjectEmpty(data)) notifyViews("EmptyObject", Boolean.TRUE);
        else notifyViews("EmptyObject", Boolean.FALSE);
        notifyViews("SensorData", data);
        if(input.isHistory()) notifyViews("HistoryMode", true);
        else notifyViews("HistoryMode", false);
    }
}
