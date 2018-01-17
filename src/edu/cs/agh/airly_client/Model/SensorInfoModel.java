package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.RESTClient.AirlyAPIClient;
import edu.cs.agh.airly_client.RESTClient.RESTClientException;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Threading.MapPointTask;
import edu.cs.agh.airly_client.Threading.SinglePointTask;

import java.io.IOException;
import java.util.concurrent.*;

/**
 *  Model responsible for handing with sensorDetails running mode.
 */
public class SensorInfoModel extends Model {
    /**
     * Implementation of abstract method from Model class.
     *
     * @param input Parsed input from command-line.
     * @throws IOException when error with server connection occurs.
     * @throws InterruptedException when somewhat process is killed while
     * trying to reconnect to server.
     * @throws RESTClientException when inner RESTClient error occurs.
     * @throws ExecutionException when error while multithreading.
     */
    @Override
    public void processData(ProgramInput input)
            throws IOException, InterruptedException, RESTClientException, ExecutionException {
        AirlyAPIClient AirlyAPI = new AirlyAPIClient(input.getAPIKey());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<MapPoint> data;
        Future<SingleSensor> sensorInfo;
        if(input.getSensorId() == null){
            data = executor.submit(
                    new MapPointTask(AirlyAPI, input.getLatitude(), input.getLongitude()));
        }
        else {
            data = executor.submit(new MapPointTask(AirlyAPI, input.getSensorId()));
        }
        if(input.isSensorDetailsWithId())
            sensorInfo = executor.submit(new SinglePointTask(AirlyAPI, input.getSensorId()));
        else
            sensorInfo = executor.submit(
                    new SinglePointTask(AirlyAPI, input.getLongitude(), input.getLatitude()));
        MapPoint dataRes;
        SingleSensor sensorInfoRes;
        try {
            dataRes = data.get();
            sensorInfoRes = sensorInfo.get();
        } finally {
            executor.shutdown();
        }
        if(isObjectEmpty(dataRes)) notifyViews("EmptyObject", Boolean.TRUE);
        else notifyViews("EmptyObject", Boolean.FALSE);
        notifyViews("SensorData", dataRes);
        if(isObjectEmpty(sensorInfoRes)) notifyViews("EmptyInfoObject", Boolean.TRUE);
        else notifyViews("EmptyInfoObject", Boolean.FALSE);
        notifyViews("SensorInfo", sensorInfoRes);
        if(input.isHistory()) notifyViews("HistoryMode", Boolean.TRUE);
        else notifyViews("HistoryMode",  Boolean.FALSE);
    }
}
