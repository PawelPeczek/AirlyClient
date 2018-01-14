package edu.cs.agh.airly_client.Parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Class that provides core features of command-line argument parser.
 */
public class ArgumentParser {

    /**
     * Public function that is responsible for running parsing process.
     *
     * @param args Command line arguments.
     * @return Valid ProgramInput object.
     * @throws ArgumentsParserException if errors encountered while
     * argument parsing.
     */
    public ProgramInput parseArguments(String[] args) throws ArgumentsParserException {
        // Assigning to each of args its ValidOptions value.
        ValidOptions[] assignation = assignOptionType(args);
        return produceProgramInput(args, assignation);
    }

    /**
     * Method assigns to each command-line argument its type and check whether
     * the argument type occurred before.
     *
     * @param args Command-line arguments.
     * @return Array that for each i element of args assign ValidOptions[i]
     * @throws ArgumentsParserException when the number of args is invalid or
     * duplicate argument-type encountered.
     */
    private ValidOptions[] assignOptionType(String[] args) throws ArgumentsParserException {
        if(args.length > 0){
            // HashSet to previous occurrence of the same argument type check
            HashSet<String> optTypesDetected = new HashSet<>();
            ValidOptions[] optionAssigned = new ValidOptions[args.length];
            for(int i = 0; i < args.length; i ++){
                optionAssigned[i] = getOptionType(args[i]);
                if(!optTypesDetected.contains(optionAssigned[i].name()))
                    optTypesDetected.add(optionAssigned[i].name());
                else throw new ArgumentsParserException("Duplicate option found: " + args[i]);
            }
            return optionAssigned;
        } else throw new ArgumentsParserException("No arguments given - check --h");
    }

    /**
     * Method that iterates through ValidOptions values and check which
     * of option type given string matches.
     *
     * @param arg Single command-line argument.
     * @return Option type for argument.
     * @throws ArgumentsParserException when command-line argument is not
     * a valid option.
     */
    private ValidOptions getOptionType(String arg) throws ArgumentsParserException {
        ValidOptions result = null;
        for(ValidOptions opt : ValidOptions.values()){
           if(opt.pattern.matcher(arg).matches()){
               result = opt;
               break;
           }
        }
        if(result == null) throw new ArgumentsParserException("Invalid option: " + arg);
        return result;
    }

    /**
     * Method that construct ProgramInput object from command line arguments
     * and also check tha validity of arguments.
     *
     * @param args Command-line arguments.
     * @param assignation Array that assign option type to each argument
     * @return ProgramInput object with parsed data provided by user.
     * @throws ArgumentsParserException when errors while parsing occurs.
     */
    private ProgramInput produceProgramInput(String[] args, ValidOptions[] assignation)
            throws ArgumentsParserException {
        ProgramInput result = new ProgramInput();
        // Need of mapping from ValidOption name to number of argument.
        HashMap<String, Integer> optTypesDetected = new HashMap<>();
        for(int i = 0; i < assignation.length; i++){
            optTypesDetected.put(assignation[i].name(), i);
        }
        // check if there is only one main option --h or --latitude/--longitude or --sensor-id
        onlyOneMainOption(optTypesDetected);
        if(optTypesDetected.containsKey(ValidOptions.history.name()))
            result.setHistoryTrue();
        if(optTypesDetected.containsKey(ValidOptions.sensorInfo.name()))
            // default initialize - set to false if needed
            result.setUnsupportedSensDetails(true);
        if(optTypesDetected.containsKey(ValidOptions.help.name())){
            result.setRunningMode(RunningMode.helpMode);
        } else if(optTypesDetected.containsKey(ValidOptions.latitude.name()) &&
                optTypesDetected.containsKey(ValidOptions.longitude.name())){
            // --latitude/--longitude mode -> now configuring its options.
            provideNearestSensorInputInfo(result, args, optTypesDetected);
        } else if(optTypesDetected.containsKey(ValidOptions.sensorId.name())){
            // --sensor-id mode -> now configuring its options.
            provideSingleSensorInputInfo(result, args, optTypesDetected);
        } else throw new ArgumentsParserException("Invalid arguments - check --h");
        // inserting APIKey data to output
        provideInputInfoWithAPIKey(result, optTypesDetected.containsKey(ValidOptions.APIKey.name())
                ? args[optTypesDetected.get(ValidOptions.APIKey.name())] : null);
        return result;
    }

    /**
     * Method checks whether only one main option --h or --latitude/--longitude
     * or --sensor-id was set.
     *
     * @param optTypesDetected Mapping from ValidOptions.names -> number of args
     * @throws ArgumentsParserException
     */
    private void onlyOneMainOption(HashMap<String, Integer> optTypesDetected)
            throws ArgumentsParserException {
        boolean error = false;
        if(optTypesDetected.containsKey(ValidOptions.help.name()) &&
           (optTypesDetected.containsKey(ValidOptions.sensorId.name()) ||
            optTypesDetected.containsKey(ValidOptions.latitude.name()) ||
            optTypesDetected.containsKey(ValidOptions.longitude.name())))
            error = true;
        else if((optTypesDetected.containsKey(ValidOptions.latitude.name()) ||
                optTypesDetected.containsKey(ValidOptions.longitude.name())) &&
                optTypesDetected.containsKey(ValidOptions.sensorId.name()))
            error = true;
        if(error) throw new ArgumentsParserException("Invalid arguments - check --h");
    }

    private String getAPIKeyFromEnv() throws ArgumentsParserException{
        String result;
        Map<String, String> env = System.getenv();
        if(env.containsKey("AIRLY_API_KEY") &&
                ValidOptions.APIKey.pattern.matcher("--api-key=" + env.get("AIRLY_API_KEY")).matches()){
            result = env.get("AIRLY_API_KEY");
        } else throw new ArgumentsParserException("Neither via command line args, nor in environment " +
                "valid APIKey is given. In environment - set AirlyAPIKey variable. Valid value: " +
                "32 digits in hex.");
        return result;
    }

    private void provideSingleSensorInputInfo(ProgramInput progInput, String[] args,
                                              HashMap<String, Integer> optTypesDetected)
            throws ArgumentsParserException {
        if(optTypesDetected.containsKey(ValidOptions.latitude.name()) &&
                optTypesDetected.containsKey(ValidOptions.longitude.name())){
            throw new ArgumentsParserException("Valid arguments are either (latitude, longitude) or sensorId.");
        }
        Integer sensorId = provideSensorId(args[optTypesDetected.get(ValidOptions.sensorId.name())]);
        progInput.setSensorId(sensorId);
        progInput.setRunningMode(RunningMode.sensorMeasurements);
    }

    private void provideNearestSensorInputInfo(ProgramInput progInput, String[] args,
                                               HashMap<String, Integer> optTypesDetected)
            throws ArgumentsParserException {
        if(optTypesDetected.containsKey(ValidOptions.sensorId.name()))
            throw new ArgumentsParserException("Valid arguments are either (latitude, longitude) or sensorId.");
        if(optTypesDetected.containsKey(ValidOptions.sensorInfo.name())) {
            progInput.setRunningMode(RunningMode.sensorDetails);
            progInput.setUnsupportedSensDetails(false);
        }
        else progInput.setRunningMode(RunningMode.nearestMeasurements);
        Double latitude = provideCoordValue(args[optTypesDetected.get(ValidOptions.latitude.name())]);
        Double longitude = provideCoordValue(args[optTypesDetected.get(ValidOptions.longitude.name())]);
        progInput.setLatitude(latitude);
        progInput.setLongitude(longitude);
    }

    private void provideInputInfoWithAPIKey(ProgramInput progInput, String APIKey)
            throws ArgumentsParserException {
        if(APIKey != null) progInput.setAPIKey(provideApiFromArgumentString(APIKey));
        else progInput.setAPIKey(getAPIKeyFromEnv());
    }

    private Integer provideSensorId(String arg){
        Matcher matcher = ValidOptions.sensorId.pattern.matcher(arg);
        Integer result;
        if(matcher.find()){
            result = Integer.parseInt(matcher.group(2));
        } else throw new IllegalArgumentException("Serious error. Error code 12-990. Please contact author.");
        return result;
    }


    private Double provideCoordValue(String arg){
        Matcher[] matchers = {
                        ValidOptions.latitude.pattern.matcher(arg),
                        ValidOptions.longitude.pattern.matcher(arg) };
        Double result = null;
        for (Matcher matcher : matchers){
            if(matcher.find()){
                result = Double.parseDouble(matcher.group(2));
                break;
            }
        }
        if(result == null)
            throw new IllegalArgumentException("Serious error. Error code 12-991. Please contact author.");
        return result;
    }

    private String provideApiFromArgumentString(String arg){
        Matcher matcher = ValidOptions.APIKey.pattern.matcher(arg);
        String result;
        if(matcher.find()){
            result = matcher.group(2);
        } else throw new IllegalArgumentException("Serious error. Error code 12-992. Please contact author.");
        return result;
    }

}
