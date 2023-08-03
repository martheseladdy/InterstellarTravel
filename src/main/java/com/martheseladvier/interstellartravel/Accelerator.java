package com.martheseladvier.interstellartravel;
import java.util.List;

public class Accelerator {

    public Accelerator(int id, String name, List<Connection> connections){
        this.id = id;
        this.name = name;
        this.connections = connections;
    }
    public int id;
    public String name = "";
    public List<Connection> connections = null;

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public List<Connection> getConnections(){
        return this.connections;
    }
    public void setId(int id){
        this.id= id;
    }

    public void setName(String name){
        this.name= name;
    }

    public void setConnections(List<Connection> connections){
        this.connections= connections;
    }


}
