package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Parser.NearestSensorInfoModel;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Views.NearestSensorInfoView;

import java.io.IOException;

public class NearestSensorInfoController extends Controller {
    public NearestSensorInfoController(){
        model = new NearestSensorInfoModel();
        view = new NearestSensorInfoView();
        model.addObserver(view);
    }
    public void mainAction(ProgramInput input) throws IOException {
        model.processData(input);
        view.renderView();
    }
}
