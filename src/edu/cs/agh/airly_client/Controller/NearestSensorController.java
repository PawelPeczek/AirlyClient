package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.NearestSensorModel;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Views.NearestSensorView;

import java.io.IOException;

public class NearestSensorController extends Controller {
    public NearestSensorController(){
        model = new NearestSensorModel();
        view = new NearestSensorView();
        model.addObserver(view);
    }

    public void mainAction(ProgramInput input) throws IOException {
        model.processData(input);
        view.renderView();
    }
}
