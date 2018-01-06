package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.SpecSensorModel;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Views.SpecSensorView;

import java.io.IOException;

public class SpecSensorController extends Controller {
    public SpecSensorController(){
        model = new SpecSensorModel();
        view = new SpecSensorView();
        model.addObserver(view);
    }
    public void mainAction(ProgramInput input) throws IOException {
        model.processData(input);
        view.renderView();
    }
}
