package edu.cs.agh.airly_client.Parser;


import java.util.regex.Pattern;

public class ProgramInput {

    private RunningMode runningMode;
    private Double latitude;
    private Double longitude;
    private Integer sensorId;
    private String APIKey;
    private boolean history;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        if(latitude.compareTo(0.) < 0 || latitude.compareTo(90.) > 0)
            throw new IllegalArgumentException("Latitude must be in range [0;90]");
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        if(longitude.compareTo(0.) < 0 || longitude.compareTo(180.) > 0)
            throw new IllegalArgumentException("Latitude must be in range [0;180]");
        this.longitude = longitude;
    }

    public String getAPIKey() {
        return APIKey;
    }

    public void setAPIKey(String APIKey) {
        if(!Pattern.compile("^[0-9a-fA-F]{32}$").matcher(APIKey).matches())
            throw new IllegalArgumentException("Trying to assign invalid value of APIKey." +
                    "The valid one: 32 digits in hex.");
        this.APIKey = APIKey;
    }

    public RunningMode getRunningMode() {
        return runningMode;
    }

    public void setRunningMode(RunningMode runningMode) {
        this.runningMode = runningMode;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistoryTrue() {
        this.history = true;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) throws ArgumentsParserException {
        if(sensorId.compareTo(0) < 0) throw new ArgumentsParserException("Bad setSensorId value!");
        this.sensorId = sensorId;
    }

}
