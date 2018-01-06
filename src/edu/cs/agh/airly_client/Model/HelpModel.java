package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.Parser.ProgramInput;

public class HelpModel extends Model{
    @Override
    public void processData(ProgramInput input) {
        notifyViews("HelpContent","HELP PAGE:");
    }
}
