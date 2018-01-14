package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.HelpModel;
import edu.cs.agh.airly_client.Views.HelpView;

/**
 * Controller associated to helpMode.
 */
public class HelpController extends Controller {
    public HelpController(){
        model = new HelpModel();
        views.add(new HelpView());
        model.addObserver(views);
    }


}
