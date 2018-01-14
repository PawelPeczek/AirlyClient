package edu.cs.agh.airly_client.Views;

import edu.cs.agh.airly_client.JSON.SingleSensor;

/**
 * View associated with mode --latitude/--longitude (with --sensor-info)
 */
public class NearestSensorInfoView extends View{
    /**
     * Implementation of abstract method from base class.
     * Method checks if appropriate view element is present in data HashMap
     * and if so - it's printed. Otherwise appropriate action takes place.
     */
    @Override
    public void renderView() {
        System.out.println(generateHeaderMessage());
        System.out.println("See how good the air quality is:\n");
        if (data.containsKey("SensorInfo")){
            SingleSensor nearestSensor = (SingleSensor) (data.get("SensorInfo"));
            if(data.containsKey("EmptyObject") && data.get("EmptyObject").equals(Boolean.TRUE))
                System.out.println("Info data for sensor unavailable");
            else {
                System.out.println(generateAsciiArt(nearestSensor.getPollutionLevel()));
                System.out.println(generateInfoAboutSensor(nearestSensor));
                if (data.containsKey("HistoryMode") && data.get("HistoryMode").equals(Boolean.TRUE))
                    System.out.println(ANSI_YELLOW + "WARNING!" + ANSI_RESET
                            + "\n--sensor-info doesn't work with --history");
            }
        } else System.out.println(generateNotFoundElementMessage("nearest sensor info"));
    }

    /**
     * Implementation of abstract method from base class.
     * @return NearestSensorInfoView header.
     */
    @Override
    protected String generateHeaderMessage() {
        return "\n******************************************************************\n" +
                "*                                                                *\n" +
                "*                        Nearest sensor info                     *\n" +
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
    private String generateInfoAboutSensor(SingleSensor nearestSensor){
        return "ID: " + (nearestSensor.getId() != null ? nearestSensor.getId() : "N/A") +
                "\n" +
                "Distance: " +
                (nearestSensor.getDistance() != null ?
                        Math.round(nearestSensor.getDistance()) + "m" : "N/A") +
                "\n" +
                "Location: " +
                (nearestSensor.getLocation() != null ?
                    prepareLocalizationInfo(nearestSensor.getLocation()) : "N/A") +
                "\n" +
                "Address: " + (nearestSensor.getAddress() != null ?
                    prepareAddressInfo(nearestSensor.getAddress()) : "N/A") +
                "\n" +
                "Vendor: " +
                (nearestSensor.getVendor() != null ? nearestSensor.getVendor() : "N/A") +
                "\n" +
                "Name: " +
                (nearestSensor.getName() != null ? nearestSensor.getName() : "N/A") +
                "\n" +
                generateBasicMeasurInfo(nearestSensor);
    }

}
