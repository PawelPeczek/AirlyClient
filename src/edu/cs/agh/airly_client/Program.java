package edu.cs.agh.airly_client;

import edu.cs.agh.airly_client.HTTPclient.AirlyAPIClient;

public class Program {
    public static void main(String[] args){
        System.out.println("Works.");
        AirlyAPIClient client = new AirlyAPIClient();
        try{
            client.getMeasurementsForMapPoint(50.09, 20.02);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
