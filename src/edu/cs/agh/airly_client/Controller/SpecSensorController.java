package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.SpecSensorModel;
import edu.cs.agh.airly_client.Views.SensorMeasurementView;
import edu.cs.agh.airly_client.Views.SpecSensorView;

/**
 * Controller associated to sensorMeasurements mode.
 */
public class SpecSensorController extends Controller {
    public SpecSensorController(){
        model = new SpecSensorModel();
        views.add(new SensorMeasurementView());
        views.add(new SpecSensorView());
        model.addObserver(views);
    }
}
