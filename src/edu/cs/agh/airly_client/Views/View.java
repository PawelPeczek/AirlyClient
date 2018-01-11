package edu.cs.agh.airly_client.Views;

import edu.cs.agh.airly_client.JSON.Address;
import edu.cs.agh.airly_client.JSON.BasicMeasurement;
import edu.cs.agh.airly_client.JSON.Location;
import edu.cs.agh.airly_client.JSON.SensorData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class View {

    protected final String ANSI_RED = "\u001B[31m";
    protected final String ANSI_GREEN = "\u001B[32m";
    protected final String ANSI_YELLOW = "\u001B[33m";
    protected final String ANSI_RESET = "\u001B[0m";

    protected HashMap<String, Object> data = new HashMap<>();

    public abstract void renderView();

    public void update(String name, Object data){
        this.data.put(name, data);
    }

    protected String generateImageNotFound(){
        return "\n" +
                "\n" +
                " +-+-+-+-+-+ +-+-+-+ +-+-+-+-+-+\n" +
                " |I|M|A|G|E| |N|O|T| |F|O|U|N|D|\n" +
                " +-+-+-+-+-+ +-+-+-+ +-+-+-+-+-+\n" +
                "\n";
    }

    protected String generateNAImage(){
        return "\n" +
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
    }

    protected abstract String generateHeaderMessage();

    protected String generateNotFoundElementMessage(String name){
        return String.format("Information about %s not found.", name);
    }

    protected String generateAsciiArt(Integer PolLvl){
        StringBuilder result = new StringBuilder();
        if(PolLvl != null){
            Conditions cond = mapPolLvlToCondition(PolLvl);
            try (BufferedReader br = new BufferedReader(new FileReader(cond.path))) {
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    result.append(sCurrentLine);
                    result.append("\n");
                }
            } catch (IOException e) {
                result.delete(0, result.length());
                result.append(generateImageNotFound());
            }
        } else result.append(generateNAImage());
        result.append("\n");
        return result.toString();
    }

    private Conditions mapPolLvlToCondition(Integer polutionLevel){
        Conditions result;
        if(polutionLevel.compareTo(2) <= 0) result = Conditions.OK;
        else if (polutionLevel.equals(3)) result = Conditions.QuiteOK;
        else if (polutionLevel.equals(4)) result = Conditions.Average;
        else if (polutionLevel.equals(5)) result = Conditions.Bad;
        else result = Conditions.Fatal;
        return result;
    }

    protected String mapPMToColor(Double PMValue){
        String result;
        if(PMValue.compareTo(50.) <= 0) result = ANSI_GREEN;
        else if(PMValue.compareTo(100.) <= 0) result = ANSI_YELLOW;
        else result = ANSI_RED;
        return result;
    }

    private String mapPolLvlToColor(Integer PolLvl){
        String result;
        if (PolLvl.compareTo(2) <= 0) result = ANSI_GREEN;
        else if (PolLvl.compareTo(4) <= 0) result = ANSI_YELLOW;
        else result = ANSI_RED;
        return  result;
    }

    private String formatMeasurementTime(String time){
        Pattern timePredicate = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})[A-Z](\\d{2}:\\d{2}:\\d{2}).*$");
        String result;
        Matcher matcher = timePredicate.matcher(time);
        if(matcher.find()){
            result = matcher.group(1) + " " + matcher.group(2);
        } else result = "N/A";
        return result;
    }

    protected String prepareLocalizationInfo(Location loc){
        return loc!= null && loc.getLatitude() != null && loc.getLongitude() != null ?
               loc.getLatitude() + "°N, " + loc.getLongitude() + "°E" : "N/A";
    }

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

    private String generateBasicPolutionInfo(BasicMeasurement measurement){
        StringBuilder result = new StringBuilder();
        if(measurement != null){
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
        return result.toString();
    }

    protected String generateHistoricalStats(LinkedList<SensorData> history){
        StringBuilder result = new StringBuilder();
        if(history != null){
            for(SensorData entry : history){
                if(entry.getMeasurements() == null) continue;
                result.append("From: ");
                result.append(formatMeasurementTime(entry.getFromDateTime()));
                result.append(" to: ");
                result.append(formatMeasurementTime(entry.getTillDateTime()));
                result.append("\n");
                result.append(generateBasicPolutionInfo(entry.getMeasurements()));
                result.append("\n\n");
            }
        }
        if(result.length() == 0) result.append("No data!");
        return result.toString();
    }
}
