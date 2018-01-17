package edu.cs.agh.airly_client.Dispatcher;

import edu.cs.agh.airly_client.Controller.HelpController;
import edu.cs.agh.airly_client.Controller.SensorMeasurementsController;
import edu.cs.agh.airly_client.Controller.SensorInfoController;
import edu.cs.agh.airly_client.RESTClient.RESTClientException;
import edu.cs.agh.airly_client.Parser.ProgramInput;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Class that is responsible for running appropriate
 * controller to RunningMode
 */
public class Dispatcher {
    /**
     * Method runs appropriate controller ro RunningMode.
     * @param progInput Parsed user input.
     * @throws IOException when IO error with file or server connection
     * occurs.
     * @throws InterruptedException when program is terminated while
     * trying to reconnect to server.
     * @throws RESTClientException when internal RESTClient error occurs.
     * @throws ExecutionException when error while multithreading.
     */
    public void dispatch(ProgramInput progInput)
            throws IOException, InterruptedException, RESTClientException,
            ExecutionException {
        switch (progInput.getRunningMode()){
            case helpMode:
                new HelpController().mainAction(progInput);
                break;
            case sensorDetails:
                new SensorInfoController().mainAction(progInput);
                break;
            case sensorMeasurements:
                if(progInput.isSensorDetailsWithId())
                    new SensorInfoController().mainAction(progInput);
                else
                    new SensorMeasurementsController().mainAction(progInput);
                break;
            case nearestMeasurements:
                new SensorMeasurementsController().mainAction(progInput);
                break;
        }
    }
}
