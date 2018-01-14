package edu.cs.agh.airly_client.Parser;

import java.util.regex.Pattern;

/**
 * Enum that determines valid command-line arguments passed
 * while program running. Thanks to storing pattern (provided via private
 * constructor) it's easy then to check if element from args[] is
 * a specified valid option.
 */
public enum ValidOptions {
    latitude("^(--latitude=)([0-9\\.]+)$"),
    longitude("^(--longitude=)([0-9\\.]+)$"),
    sensorId("^(--sensor-id=)([0-9]+)$"),
    sensorInfo("^--sensor-info$"),
    APIKey("^(--api-key=)([0-9a-fA-F]{32})$"),
    history("^--history$"),
    help("^--h$");

    /**
     * Pattern that each of ValidOptions follow.
     */
    public final Pattern pattern;

    /**
     * Private constructor that allows to provide a pattern for each element
     * from ValidOptions.
     *
     * @param pattern Pattern of option.
     */
    private ValidOptions(String pattern){
        this.pattern = Pattern.compile(pattern);
    }
}
