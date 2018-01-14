package edu.cs.agh.airly_client.Dispatcher;

import edu.cs.agh.airly_client.Controller.HelpController;
import edu.cs.agh.airly_client.Controller.NearestSensorController;
import edu.cs.agh.airly_client.Controller.NearestSensorInfoController;
import edu.cs.agh.airly_client.Controller.SpecSensorController;
import edu.cs.agh.airly_client.RESTClient.RESTClientException;
import edu.cs.agh.airly_client.Parser.ProgramInput;

import java.io.IOException;

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
     */
    public void dispatch(ProgramInput progInput)
            throws IOException, InterruptedException, RESTClientException {
        switch (progInput.getRunningMode()){
            case helpMode:
                new HelpController().mainAction(progInput);
                break;
            case sensorDetails:
                new NearestSensorInfoController().mainAction(progInput);
                break;
            case sensorMeasurements:
                new SpecSensorController().mainAction(progInput);
                break;
            case nearestMeasurements:
                new NearestSensorController().mainAction(progInput);
                break;
        }
    }
}
