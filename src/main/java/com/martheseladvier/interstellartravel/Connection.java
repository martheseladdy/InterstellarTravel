package com.martheseladvier.interstellartravel;

public class Connection extends Accelerator {


    public Connection(String id, String name, int distance){
        super(id, name, null);
        this.id = id;
        this.name = name;
        this.distance = distance;
    }
    public int distance = 0;

    public int getDistance(){
        return this.distance;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }
}
