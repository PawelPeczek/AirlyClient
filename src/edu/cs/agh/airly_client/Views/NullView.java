package edu.cs.agh.airly_client.Views;

public class NullView extends View{
    @Override
    public void renderView() {
        System.out.println("ERROR PAGE:");
        if(data.containsKey("content"))
            System.out.println(data.get("content").toString());
    }

    @Override
    protected String generateHeaderMessage() {
        return null;
    }
}
