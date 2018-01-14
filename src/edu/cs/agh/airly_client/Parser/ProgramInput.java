package edu.cs.agh.airly_client.Parser;


import java.util.regex.Pattern;

/**
 * Class that describe program input. It allows to make some
 * computation by ArgumentParser and then be passed to further
 * processing without being aware of other objects of existing
 * and behavior of ArgumentParser.
 */
public class ProgramInput {

    /**
     * Running mode of current instance of program.
     */
    private RunningMode runningMode;
    /**
     * value of --latitude
     */
    private Double latitude;
    /**
     * value of --longitude
     */
    private Double longitude;
    /**
     * value of --sensor-id
     */
    private Integer sensorId;
    /**
     * value of --api-key or environmental variable AIRLY_API_KEY
     */
    private String APIKey;
    /**
     * determines whether --history was set
     */
    private boolean history;
    /**
     * determines whether --sensor-details was set with connection to --sensor-id
     */
    private boolean unsupportedSensDetails = false;

    /**
     * Latitude getter.
     *
     * @return Value of latitude.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Latitude setter.
     *
     * @param latitude New value of latitude.
     * @throws IllegalArgumentException when latitude is not in range [0;90].
     */
    public void setLatitude(Double latitude) {
        if(latitude.compareTo(0.) < 0 || latitude.compareTo(90.) > 0)
            throw new IllegalArgumentException("Latitude must be in range [0;90]");
        this.latitude = latitude;
    }

    /**
     * Longitude getter.
     *
     * @return Longitude value.
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Longitude setter.
     *
     * @param longitude New value of longitude.
     * @throws IllegalArgumentException when longitude is not in range [0;180]
     */
    public void setLongitude(Double longitude) {
        if(longitude.compareTo(0.) < 0 || longitude.compareTo(180.) > 0)
            throw new IllegalArgumentException("Latitude must be in range [0;180]");
        this.longitude = longitude;
    }

    /**
     * APIKey getter.
     *
     * @return Value of APIKey.
     */
    public String getAPIKey() {
        return APIKey;
    }

    /**
     * APIKey setter.
     * It is assumed that APIKey must be 128-bit HEX-value String
     *
     * @param APIKey New value of APIKey.
     * @throws IllegalArgumentException when new APIKey doesn't follow pattern.
     */
    public void setAPIKey(String APIKey) {
        if(!Pattern.compile("^[0-9a-fA-F]{32}$").matcher(APIKey).matches())
            throw new IllegalArgumentException("Trying to assign invalid value of APIKey." +
                    "The valid one: 32 digits in hex.");
        this.APIKey = APIKey;
    }

    /**
     * RunningMode getter.
     *
     * @return Value of runningMode.
     */
    public RunningMode getRunningMode() {
        return runningMode;
    }

    /**
     * RunningMode setter
     *
     * @param runningMode New value of runningMode
     */
    public void setRunningMode(RunningMode runningMode) {
        this.runningMode = runningMode;
    }

    /**
     * History getter.
     *
     * @return Value of history.
     */
    public boolean isHistory() {
        return history;
    }

    /**
     * Method changes the history value into true.
     */
    public void setHistoryTrue() {
        this.history = true;
    }

    /**
     * SensorId getter.
     *
     * @return Value of sensorId.
     */
    public Integer getSensorId() {
        return sensorId;
    }

    /**
     * SensorId setter.
     *
     * @param sensorId New value of sensorId.
     * @throws ArgumentsParserException when sensorId < 0.
     */
    public void setSensorId(Integer sensorId) throws ArgumentsParserException {
        if(sensorId.compareTo(0) < 0) throw new ArgumentsParserException("Bad setSensorId value!");
        this.sensorId = sensorId;
    }

    /**
     * UnsupportedSensDetails getter.
     *
     * @return Value of unsupportedSensDetails.
     */
    public boolean isUnsupportedSensDetails() {
        return unsupportedSensDetails;
    }

    /**
     * UnsupportedSensDetails setter.
     *
     * @param val New value of unsupportedSensDetails.
     */
    public void setUnsupportedSensDetails(boolean val) {
        this.unsupportedSensDetails = val;
    }
}
