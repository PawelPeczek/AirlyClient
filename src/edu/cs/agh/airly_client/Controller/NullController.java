package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.Parser.ProgramInput;

import java.io.IOException;

public class NullController extends Controller{
    public void mainAction(ProgramInput input) throws IOException {
        model.processData(input);
        view.renderView();
    }
}
