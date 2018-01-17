package edu.cs.agh.airly_client;

import com.google.gson.JsonSyntaxException;
import edu.cs.agh.airly_client.Dispatcher.Dispatcher;
import edu.cs.agh.airly_client.RESTClient.RESTClientException;
import edu.cs.agh.airly_client.Parser.ArgumentParser;
import edu.cs.agh.airly_client.Parser.ArgumentsParserException;

import java.util.concurrent.ExecutionException;


public class Program {
    public static void main(String[] args){
        try{
            ArgumentParser parser = new ArgumentParser();
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.dispatch(parser.parseArguments(args));
        } catch (JsonSyntaxException e){
            System.out.println("Unexpected troubles with server occurred. Please try again in a while");
        } catch (RESTClientException | ArgumentsParserException e){
            System.out.println(e.getMessage());
        } catch(InterruptedException e){
            System.out.println("Program was terminated while attempting one of three tries to connect to server");
        } catch(ExecutionException e){
            System.out.println("Error while parallel execution:");
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Some serious exception. Please contact the creator. Error message:");
            if(e.getMessage() != null) System.out.println(e.getMessage());
            else System.out.println("No message given.");
        }
    }
}
