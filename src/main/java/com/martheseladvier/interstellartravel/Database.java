package com.martheseladvier.interstellartravel;
import java.util.List;
import java.util.ArrayList;

public class Database implements DatabaseInterface{

    public void connect(){

    }

    public Accelerator getAccelerator(int id){
        return new Accelerator(0, null, null);
    }

    public List<Accelerator> getAllAccelerators(){
        return new ArrayList<Accelerator>();
    }
}
