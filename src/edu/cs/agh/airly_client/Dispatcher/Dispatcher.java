package edu.cs.agh.airly_client.Dispatcher;

import edu.cs.agh.airly_client.Controller.HelpController;
import edu.cs.agh.airly_client.Controller.NearestSensorController;
import edu.cs.agh.airly_client.Controller.NearestSensorInfoController;
import edu.cs.agh.airly_client.Controller.SpecSensorController;
import edu.cs.agh.airly_client.HTTPclient.HTTPClientException;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.ProcessingException;

import java.io.IOException;

public class Dispatcher {
    public void dispatch(ProgramInput progInput)
            throws IOException, InterruptedException, HTTPClientException, ProcessingException {
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
