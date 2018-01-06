package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Model.Model;
import edu.cs.agh.airly_client.Model.NullModel;
import edu.cs.agh.airly_client.Views.NullView;
import edu.cs.agh.airly_client.Views.View;


public abstract class Controller {
    protected Model model;
    protected View view;
    public Controller(){
        model = new NullModel();
        view = new NullView();
        model.addObserver(view);
    }
}
