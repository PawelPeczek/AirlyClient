package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.SensorInfoModel;
import edu.cs.agh.airly_client.Views.SensorInfoView;
import edu.cs.agh.airly_client.Views.SensorMeasurementView;

/**
 * Controller associated to sensorDetails mode.
 */
public class SensorInfoController extends Controller {
    public SensorInfoController(){
        model = new SensorInfoModel();
        views.add(new SensorInfoView());
        views.add(new SensorMeasurementView());
        model.addObserver(views);
    }
}
