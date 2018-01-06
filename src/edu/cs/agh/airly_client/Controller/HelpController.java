package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.HelpModel;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Views.HelpView;

import java.io.IOException;

public class HelpController extends Controller {

    public HelpController(){
        model = new HelpModel();
        view = new HelpView();
        model.addObserver(view);
    }

    public void mainAction(ProgramInput input) throws IOException {
        model.processData(input);
        view.renderView();
    }
}
