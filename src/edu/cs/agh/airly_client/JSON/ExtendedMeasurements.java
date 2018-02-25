package edu.cs.agh.airly_client.JSON;

/**
 * POJO Class that represents extended measurement data
 * which are possible to receive from server.
 */
public class ExtendedMeasurements extends BasicMeasurement {

    private Double pm1;

    private Double windSpeed;

    private Double humidity;

    private Double pressure;

    private String windDirection;

    private Double temperature;

    public Double getPm1 () {
        return pm1;
    }

    public void setPm1 (Double pm1) {
        this.pm1 = pm1;
    }

    public Double getWindSpeed () {
        return windSpeed;
    }

    public void setWindSpeed (Double windSpeed) {
        this.windSpeed = windSpeed;
    }


    public void setPollutionLevel (Integer pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }

    @Override
    public boolean isFulfilled() {
        return (pm1 != null && super.isFulfilled());
    }

    public Double getHumidity () {
        return humidity;
    }

    public void setHumidity (Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure () {
        return pressure;
    }

    public void setPressure (Double pressure) {
        this.pressure = pressure;
    }

    public String getWindDirection () {
        return windDirection;
    }

    public void setWindDirection (String windDirection) {
        this.windDirection = windDirection;
    }

    public Double getTemperature () {
        return temperature;
    }

    public void setTemperature (Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "[pm1 = "+pm1+", airQualityIndex = "+airQualityIndex+", windSpeed = "+windSpeed+", pollutionLevel = "+pollutionLevel+", humidity = "+humidity+", pressure = "+pressure+", measurementTime = "+measurementTime+", windDirection = "+windDirection+", pm10 = "+pm10+", pm25 = "+pm25+", temperature = "+temperature+"]";
    }
}
