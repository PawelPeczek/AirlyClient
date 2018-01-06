package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Views.View;

import java.io.IOException;
import java.util.LinkedList;

public abstract class Model {
    protected LinkedList<View> observers = new LinkedList<>();
    public void addObserver(View view){
        if(!observers.contains(view)) observers.add(view);
    }
    protected void notifyViews(String name, Object data){
        for (View v: observers) {
            v.update(name, data);
        }
    }
    public abstract void processData(ProgramInput input) throws IOException;
}
