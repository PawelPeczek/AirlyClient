package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.Parser.ProgramInput;

import java.io.IOException;

public class NullModel extends Model {
    @Override
    public void processData(ProgramInput input) throws IOException {
        notifyViews("content", "Something went wrong... Please contact author.");
    }
}
