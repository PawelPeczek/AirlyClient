package edu.cs.agh.airly_client.Views;

import java.util.HashMap;

public abstract class View {
    protected HashMap<String, Object> data = new HashMap<>();
    public abstract void renderView();
    public void update(String name, Object data){
        this.data.put(name, data);
    }

}
