package edu.cs.agh.airly_client.Parser;

import java.util.regex.Pattern;

public enum ValidOptions {
    latitude("^(--latitude=)([0-9\\.]+)$"),
    longitude("^(--longitude=)([0-9\\.]+)$"),
    sensorId("^(--sensor-id=)([0-9]+)$"),
    sensorInfo("^--sensor-info$"),
    APIKey("^(--api-key=)([0-9a-fA-F]{32})$"),
    history("^--history$"),
    help("^--h$");

    public final Pattern pattern;
    private ValidOptions(String pattern){
        this.pattern = Pattern.compile(pattern);
    }
}
