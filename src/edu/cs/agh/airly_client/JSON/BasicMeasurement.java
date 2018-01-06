package edu.cs.agh.airly_client.JSON;

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

    public int getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(int pollutionLevel) {
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
}
