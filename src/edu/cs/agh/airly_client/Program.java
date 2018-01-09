package edu.cs.agh.airly_client;

import edu.cs.agh.airly_client.Dispatcher.Dispatcher;
import edu.cs.agh.airly_client.Parser.ArgumentParser;

public class Program {
    public static void main(String[] args){
        System.out.println("Works.");
        try{
            ArgumentParser parser = new ArgumentParser();
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.dispatch(parser.parseArguments(args));
        } catch(ClassCastException e){
            System.out.println("Cast exception!");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
