package edu.cs.agh.airly_client.JSON;

/**
 * POJO Class that represents basic measurement data
 * which are possible to receive from server.
 */
public abstract class BasicMeasurement {

    protected Double airQualityIndex;

    protected Integer pollutionLevel;

    protected String measurementTime;

    protected Double pm10;

    protected Double pm25;

    public Double getAirQualityIndex() {
        return airQualityIndex;
    }

    public void setAirQualityIndex(Double airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public Integer getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(Integer pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(String measurementTime) {
        this.measurementTime = measurementTime;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public boolean isFulfilled(){
        return (pm10 != null && pm25 != null && airQualityIndex != null && pollutionLevel != null);
    }
}
