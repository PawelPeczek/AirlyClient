package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.HTTPclient.HTTPClientException;
import edu.cs.agh.airly_client.Model.Model;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Views.View;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;


public abstract class Controller {
    protected Model model;
    protected HashSet<View> views = new LinkedHashSet<>();

    public void mainAction(ProgramInput input)
            throws InterruptedException, HTTPClientException, IOException {
        model.processData(input);
        for (View view : views) {
            view.renderView();
        }
    }
}
