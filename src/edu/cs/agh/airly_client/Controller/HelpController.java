package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.HelpModel;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Views.HelpView;
import edu.cs.agh.airly_client.Views.View;

import java.io.IOException;

public class HelpController extends Controller {

    public HelpController(){
        model = new HelpModel();
        views.add(new HelpView());
        model.addObserver(views);
    }


}
