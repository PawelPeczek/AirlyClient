package edu.cs.agh.airly_client.Views;

import edu.cs.agh.airly_client.JSON.ExtendedMeasurements;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import javafx.css.Match;

/**
 * View associated with modes -sensor-id and --latitude/--longitude (without --sensor-info)
 */
public class SensorMeasurementView extends View {
    /**
     * Implementation of abstract method from base class.
     * Method checks if appropriate view element is present in data HashMap
     * and if so - it's printed. Otherwise appropriate action takes place.
     */
    @Override
    public void renderView() {
        if(data.containsKey("SensorData"))
            System.out.println(generateHeaderMessage());
            if(data.containsKey("SensorData")){
                MapPoint sensor = (MapPoint) data.get("SensorData");
                if(data.containsKey("EmptyObject") && data.get("EmptyObject").equals(Boolean.TRUE))
                    System.out.println("Measurement data for sensor unavailable");
                else {
                    System.out.println(generateAsciiArt(sensor.getCurrentMeasurements() != null ?
                            sensor.getCurrentMeasurements().getPollutionLevel() : null));
                    System.out.println(generateInfoAboutSensor(sensor));
                }
                if(data.containsKey("HistoryMode") && data.get("HistoryMode").equals(Boolean.TRUE)){
                    System.out.println("\n\nHistorical data:");
                    System.out.println(generateHistoricalStats(sensor.getHistory()));
                }
            } else System.out.println(generateNotFoundElementMessage("nearest sensor detailed measurements"));
    }

    /**
     * Implementation of abstract method from base class.
     * @return SensorMeasurementView header.
     */
    @Override
    protected String generateHeaderMessage() {
        return "\n******************************************************************\n" +
                "*                                                                *\n" +
                "*                          Sensor measurements                   *\n" +
                "*                                                                *\n" +
                "******************************************************************\n";
    }

    /**
     * Method responsible for formatting data about sensor which was
     * received from server. It converts value to appropriate form.
     *
     * @param nearestSensor Data about nearest sensor from server.
     * @return Formatted output.
     */
    private String generateInfoAboutSensor(MapPoint nearestSensor){
        StringBuilder result = new StringBuilder();
        ExtendedMeasurements currMeasur = nearestSensor.getCurrentMeasurements();
        result.append("Current measurements:\n");
        if(currMeasur != null){
            result.append("Wind speed: ")
                  .append(currMeasur.getWindSpeed() != null ?
                    Math.round(currMeasur.getWindSpeed()) + "mps" : "N/A")
                  .append("\n")
                  .append("Humidity: ")
                  .append(currMeasur.getHumidity() != null ?
                    Math.round(currMeasur.getHumidity()) + "%" : "N/A")
                  .append("\n")
                  .append("Pressure: ")
                  .append(currMeasur.getPressure() != null ?
                    Math.round(currMeasur.getPressure() / 100) + "hPa" : "N/A")
                  .append("\n")
                  .append("Temperature: ")
                  .append(currMeasur.getTemperature() != null ?
                    Math.round(currMeasur.getTemperature()) + "°C" : "N/A")
                  .append("\n")
                  .append(generateBasicMeasurInfo(currMeasur))
                  .append("\n")
                  .append("PM1: ")
                  .append(currMeasur.getPm1() != null ?
                    mapPMToColor(currMeasur.getPm1()) + Math.round(currMeasur.getPm1()) + ANSI_RESET : "N/A");
        } else result.append("Not given for nearest station :(");
        result.append("\n");
        return result.toString();
    }
}
