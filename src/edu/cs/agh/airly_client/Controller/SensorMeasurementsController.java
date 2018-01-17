package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.NearestSensorMeasurementModel;
import edu.cs.agh.airly_client.Views.SensorMeasurementView;

/**
 * Controller assigned to nearestMeasurements mode
 */
public class NearestSensorMeasurementsController extends Controller {
    public NearestSensorMeasurementsController(){
        model = new NearestSensorMeasurementModel();
        views.add(new SensorMeasurementView());
        model.addObserver(views);
    }
}
