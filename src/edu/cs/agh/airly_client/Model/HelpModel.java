package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.Parser.ProgramInput;

/**
 * Model responsible for processing data in helpMode.
 */
public class HelpModel extends Model{
    /**
     * Implementation of abstract method from Model class.
     *
     * @param input Parsed input from command-line.
     */
    @Override
    public void processData(ProgramInput input) {
        String helpContent = "HELP PAGE:\nParameters allowed in program:\n" +
                "--latitude=val both with --longitude=val - presenting data for nearest sensor\n" +
                "--sensor-id=val - presenting data from specific sensor\n" +
                "--api-key=val - (optional) API key\n" +
                "--history - (optional) showing earlier measurements\n" +
                "--sensor-info - (optional) with latitude/longitude - showing location details of nearest sensor";
        notifyViews("HelpContent", helpContent);
    }
}
