package com.martheseladvier.interstellartravel;
import java.util.List;

public class Accelerator {

    public Accelerator(String id, String name, List<Connection> connections){
        this.id = id;
        this.name = name;
        this.connections = connections;
    }
    public String id = "";
    public String name = "";
    public List<Connection> connections = null;

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public List<Connection> getConnections(){
        return this.connections;
    }
    public void setId(String id){
        this.id= id;
    }

    public void setName(String name){
        this.name= name;
    }

    public void setConnections(List<Connection> connections){
        this.connections= connections;
    }


}
