package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.SensorMeasurementModel;
import edu.cs.agh.airly_client.Views.SensorMeasurementView;

/**
 * Controller assigned to nearestMeasurements and sensorMeasurements modes
 */
public class SensorMeasurementsController extends Controller {
    public SensorMeasurementsController(){
        model = new SensorMeasurementModel();
        views.add(new SensorMeasurementView());
        model.addObserver(views);
    }
}
