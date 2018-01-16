package edu.cs.agh.airly_client.Views;

import edu.cs.agh.airly_client.JSON.Address;
import edu.cs.agh.airly_client.JSON.BasicMeasurement;
import edu.cs.agh.airly_client.JSON.Location;
import edu.cs.agh.airly_client.JSON.SensorData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * abstract class View
 * Provides abstract base class for other Views in application.
 * Groups methods used by more than one specific view class.
 */
public abstract class View {

    /**
     * ANSI_RED special char code used to formatting output.
     */
    protected final String ANSI_RED = "\u001B[31m";
    /**
     * ANSI_GREEN special char code used to formatting output.
     */
    protected final String ANSI_GREEN = "\u001B[32m";
    /**
     * ANSI_YELLOW special char code used to formatting output.
     */
    protected final String ANSI_YELLOW = "\u001B[33m";
    /**
     * ANSI_RESET special char code used to formatting output.
     */
    protected final String ANSI_RESET = "\u001B[0m";

    /**
     * HashMap that stores Objects to show in view
     */
    protected HashMap<String, Object> data = new HashMap<>();

    /**
     * ASCII-art with information "IMAGE NOT FOUND" used in situation
     * when program somewhat cannot load required ASCII-art from file.
     */
    private final String imageNotFound = "\n" +
            "\n" +
            " +-+-+-+-+-+ +-+-+-+ +-+-+-+-+-+\n" +
            " |I|M|A|G|E| |N|O|T| |F|O|U|N|D|\n" +
            " +-+-+-+-+-+ +-+-+-+ +-+-+-+-+-+\n" +
            "\n";


    /**
     * ASCII-art with question mark (?) used when server JSON output
     * doesn't contain appropriate information about pollution
     * level.
     */
    private final String NAImage = "\n" +
            "\n" +
            "  _.--,-```-.    \n" +
            " /    /      '.  \n" +
            "/  ../         ; \n" +
            "\\  ``\\  .``-    '\n" +
            " \\ ___\\/    \\   :\n" +
            "       \\    :   |\n" +
            "       |    ;  . \n" +
            "      ;   ;   :  \n" +
            "     /   :   :   \n" +
            "     `---'.  |   \n" +
            "      `--..`;    \n" +
            "    .--,_        \n" +
            "    |    |`.     \n" +
            "    `-- -`, ;    \n" +
            "      '---`\"     \n" +
            "                 \n" +
            "\n";

    /**
     * Function responsible for generating output at the console window.
     */
    public abstract void renderView();

    /**
     * Part of implementation of Observer design pattern.
     *
     * @param name Name of object to be further show in view.
     * @param data Actual data-object.
     */
    public void update(String name, Object data){
        this.data.put(name, data);
    }


    /**
     * Abstract method which ensures that each derived specific-view class
     * has to implement this - in general this method should print
     * the header of specific view.
     *
     * @return Header of specific view.
     */
    protected abstract String generateHeaderMessage();

    /**
     * Method responsible for generating information about missing
     * part of required information in server JSON response.
     *
     * @param name Name of element which misses in the server JSON response.
     * @return Text of information.
     */
    protected String generateNotFoundElementMessage(String name){
        return String.format("Information about %s not found.", name);
    }

    /**
     * Method generates ASCII-art depends on PollutionLevel data from server
     * JSON response. It loads the "images" from file (resource location in
     * jar is encoded in URI property of Conditions enum) and handle with
     * loading errors by inserting to its output imageNotFound ASCII-art.
     * Method also handle with a situation when server JSON response doesn't
     * contain PollutionLevel data by inserting to its output NAImage ASCII-art.
     *
     * @param PolLvl PollutionLevel from server JSON response.
     * @return ASCII-art appropriate to PollutionLevel.
     */
    protected String generateAsciiArt(Integer PolLvl){
        StringBuilder result = new StringBuilder();
        if(PolLvl != null){
            Conditions cond = mapPolLvlToCondition(PolLvl);
            try (BufferedReader br =
                         new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(cond.URI)))) {
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    result.append(sCurrentLine);
                    result.append("\n");
                }
            } catch (IOException e) {
                result.delete(0, result.length());
                result.append(imageNotFound);
            }
        } else result.append(NAImage);
        result.append("\n");
        return result.toString();
    }

    /**
     * Methods ensures mapping PollutionLevel into appropriate value from
     * Conditions enum.
     * IMPORTANT!
     * Requires pollutionLevel to be not null!
     *
     * @param pollutionLevel PollutionLevel from server JSON response.
     * @return Appropriate Conditions value
     */
    private Conditions mapPolLvlToCondition(Integer pollutionLevel){
        Conditions result;
        if(pollutionLevel.compareTo(2) <= 0) result = Conditions.OK;
        else if (pollutionLevel.equals(3)) result = Conditions.QuiteOK;
        else if (pollutionLevel.equals(4)) result = Conditions.Average;
        else if (pollutionLevel.equals(5)) result = Conditions.Bad;
        else result = Conditions.Fatal;
        return result;
    }

    /**
     * Method performs mapping from PMx value (obtained from server JSON
     * response) to color from declared ANSI_GREEN, ANSI_YELLOW, ANSI_RED.
     * IMPORTANT!
     * Requires PMValue to be not null!
     *
     * @param PMValue PMx value (obtained from server JSON response).
     * @return Appropriate special color character combination.
     */
    protected String mapPMToColor(Double PMValue){
        String result;
        if(PMValue.compareTo(50.) <= 0) result = ANSI_GREEN;
        else if(PMValue.compareTo(100.) <= 0) result = ANSI_YELLOW;
        else result = ANSI_RED;
        return result;
    }

    /**
     * Method performs mapping from PollutionLevel value (obtained from server
     * JSON response) to color from declared ANSI_GREEN, ANSI_YELLOW, ANSI_RED.
     * IMPORTANT!
     * Requires PolLvl to be not null!
     *
     * @param PolLvl PollutionLevel value (obtained from server JSON response).
     * @return Appropriate special color character combination.
     */
    private String mapPolLvlToColor(Integer PolLvl){
        String result;
        if (PolLvl.compareTo(2) <= 0) result = ANSI_GREEN;
        else if (PolLvl.compareTo(4) <= 0) result = ANSI_YELLOW;
        else result = ANSI_RED;
        return  result;
    }

    /**
     * Method is responsible for formatting time info from server JSON response.
     * IMPORTANT!
     * Requires time to be not null!
     *
     * @param time Time info from server JSON response.
     * @return Time info in a nice, readable format.
     */
    private String formatMeasurementTime(String time){
        Pattern timePredicate = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})[A-Z](\\d{2}:\\d{2}:\\d{2}).*$");
        String result;
        Matcher matcher = timePredicate.matcher(time);
        if(matcher.find()){
            result = matcher.group(1) + " " + matcher.group(2);
        } else result = "N/A";
        return result;
    }

    /**
     * Method responsible for turning localization data from server JSON response
     * into readable format.
     * IMPORTANT!
     * Requires loc to be not null!
     *
     * @param loc Localization data from server JSON response.
     * @return Localization data in a nice, readable format.
     */
    protected String prepareLocalizationInfo(Location loc){
        return loc!= null && loc.getLatitude() != null && loc.getLongitude() != null ?
               loc.getLatitude() + "°N, " + loc.getLongitude() + "°E" : "N/A";
    }

    /**
     * Method responsible for formatting Address data from server JSON response.
     * IMPORTANT!
     * Requires address to be not null!
     *
     * @param address Address data from server JSON response.
     * @return Address data in a nice, readable format.
     */
    protected String prepareAddressInfo(Address address){
        StringBuilder result = new StringBuilder();
        result.append(address.getRoute() != null ? "route: " + address.getRoute() + " " : "")
              .append(address.getStreetNumber() != null  ?
                "str. number: " + address.getStreetNumber() + " " : "")
              .append(address.getLocality() != null ? "locality: " + address.getLocality() + " " : "")
              .append(address.getCountry() != null ? "country: " + address.getCountry() + " " : "");
        if(result.length() == 0) {
            result.append("N/A");
        }
        return result.toString();
    }

    /**
     * Method responsible for formatting basic measurement data from server response.
     *
     * @param measurement Measurement data from server response.
     * @return Measurement data in a nice, readable format.
     */
    protected String generateBasicMeasurInfo(BasicMeasurement measurement){
        StringBuilder result = new StringBuilder();
        if(measurement != null){
            result.append("Measurement time: ")
                  .append(measurement.getMeasurementTime() != null ?
                    formatMeasurementTime(measurement.getMeasurementTime()) : "N/A")
                  .append("\n")
                  .append(generateBasicPolutionInfo(measurement));
        }
        return result.toString();
    }

    /**
     * Method responsible for formatting basic pollution data from server response.
     *
     * @param measurement Measurement data from server response.
     * @return Pollution data in a nice, readable format. N/A if no data given!
     */
    private String generateBasicPolutionInfo(BasicMeasurement measurement){
        StringBuilder result = new StringBuilder();
        boolean onlyNA = true;
        if(measurement != null){
            if(measurement.getAirQualityIndex() != null ||
               measurement.getPollutionLevel() != null ||
               measurement.getPm10() != null ||
               measurement.getPm25() != null) onlyNA = false;
            result.append("AirQualityIndex: ")
                  .append(measurement.getAirQualityIndex() != null ?
                            Math.round(measurement.getAirQualityIndex()) : "N/A")
                  .append("\n").append("Pollution level: ")
                  .append(measurement.getPollutionLevel() != null ?
                            mapPolLvlToColor(measurement.getPollutionLevel()) +
                            measurement.getPollutionLevel().toString() + ANSI_RESET : "N/A")
                  .append("\n").append("PM10: ").append(measurement.getPm10() != null ?
                            mapPMToColor(measurement.getPm10()) +
                            Math.round(measurement.getPm10()) + ANSI_RESET : "N/A")
                  .append("\n").append("PM25: ").append(measurement.getPm25() != null ?
                            mapPMToColor(measurement.getPm25()) +
                            Math.round(measurement.getPm25()) + ANSI_RESET : "N/A");
        }
        if(onlyNA){
            result.delete(0, result.length());
            result.append("N/A");
        }
        return result.toString();
    }

    /**
     * Method responsible for generating history measurements list.
     *
     * @param history All historical measurement that was included
     *                in server response.
     * @return Nice and readable formatting of the list.
     */
    protected String generateHistoricalStats(LinkedList<SensorData> history){
        StringBuilder result = new StringBuilder();
        boolean onlyNA = true;
        if(history != null){
            for(SensorData entry : history){
                if(entry.getMeasurements() == null) continue;
                result.append("From: ");
                result.append(formatMeasurementTime(entry.getFromDateTime()));
                result.append(" to: ");
                result.append(formatMeasurementTime(entry.getTillDateTime()));
                result.append("\n");
                String lastMethInvocationRes;
                lastMethInvocationRes = generateBasicPolutionInfo(entry.getMeasurements());
                if(!lastMethInvocationRes.equals("N/A")) onlyNA = false;
                result.append(lastMethInvocationRes);
                result.append("\n\n");
            }
        }
        if(onlyNA) {
            result.delete(0, result.length());
            result.append("No data!");
        }
        return result.toString();
    }
}
