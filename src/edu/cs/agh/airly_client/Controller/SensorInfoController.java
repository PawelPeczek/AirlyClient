package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.NearestSensorInfoModel;
import edu.cs.agh.airly_client.Views.NearestSensorInfoView;

/**
 * Controller associated to sensorDetails mode.
 */
public class NearestSensorInfoController extends Controller {
    public NearestSensorInfoController(){
        model = new NearestSensorInfoModel();
        views.add(new NearestSensorInfoView());
        model.addObserver(views);
    }
}
