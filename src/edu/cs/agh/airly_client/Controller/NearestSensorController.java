package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.NearestSensorModel;
import edu.cs.agh.airly_client.Views.SensorMeasurementView;

/**
 * Controller assigned to nearestMeasurements mode
 */
public class NearestSensorController extends Controller {
    public NearestSensorController(){
        model = new NearestSensorModel();
        views.add(new SensorMeasurementView());
        model.addObserver(views);
    }
}
