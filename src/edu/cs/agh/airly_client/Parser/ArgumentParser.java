package edu.cs.agh.airly_client.Parser;

import edu.cs.agh.airly_client.Program;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;

public class ArgumentParser {

    public ProgramInput parseArguments(String[] args) throws ArgumentsParserException {
        ValidOptions[] assignation = assignOptionType(args);
        return produceProgramInput(args, assignation);
    }

    private ValidOptions[] assignOptionType(String[] args) throws ArgumentsParserException {
        if(args.length > 0){
            HashSet<String> optTypesDetected = new HashSet<>();
            ValidOptions[] optionAssigned = new ValidOptions[args.length];
            for(int i = 0; i < args.length; i ++){
                optionAssigned[i] = getOptionType(args[i]);
                System.out.println("Name test: " + optionAssigned[i].name());
                if(!optTypesDetected.contains(optionAssigned[i].name()))
                    optTypesDetected.add(optionAssigned[i].name());
                else throw new ArgumentsParserException("Duplicate option found: " + args[i]);
            }
            return optionAssigned;
        } else throw new ArgumentsParserException("No arguments given - check --help");
    }

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

    private ProgramInput produceProgramInput(String[] args, ValidOptions[] assignation)
            throws ArgumentsParserException {
        ProgramInput result = new ProgramInput();
        HashMap<String, Integer> optTypesDetected = new HashMap<>();
        for(int i = 0; i < assignation.length; i++){
            optTypesDetected.put(assignation[i].name(), i);
        }
        if(optTypesDetected.containsKey(ValidOptions.history.name()))
            result.setHistoryTrue();
        if(optTypesDetected.containsKey(ValidOptions.help.name())){
            result.setRunningMode(RunningMode.helpMode);
        } else if(optTypesDetected.containsKey(ValidOptions.latitude.name()) &&
                optTypesDetected.containsKey(ValidOptions.longitude.name())){
            provideNearestSensorInputInfo(result, args, optTypesDetected);
        } else if(optTypesDetected.containsKey(ValidOptions.sensorId.name())){
            provideSingleSensorInputInfo(result, args, optTypesDetected);
        } else throw new ArgumentsParserException("Invalid arguments - check --h");
        provideInputInfoWithAPIKey(result, optTypesDetected.containsKey(ValidOptions.APIKey.name())
                ? args[optTypesDetected.get(ValidOptions.APIKey.name())] : null);
        return result;
    }

    private String getAPIKeyFromEnv() throws ArgumentsParserException{
        String result;
        Map<String, String> env = System.getenv();
        if(env.containsKey("AirlyAPIKey") &&
                ValidOptions.APIKey.pattern.matcher(env.get("AirlyAPIKey")).matches()){
            result = env.get("AirlyAPIKey");
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
        if(optTypesDetected.containsKey(ValidOptions.sensorInfo.name()))
            progInput.setRunningMode(RunningMode.sensorDetails);
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
        System.out.println("API: " + result);
        return result;
    }

}
