package com.martheseladvier.interstellartravel;

public class Connection extends Accelerator {


    public Connection(int id, String name, int distance){
        super(id, name, null); //confirm best practise? Also confirm how to spell practise in this context?
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
