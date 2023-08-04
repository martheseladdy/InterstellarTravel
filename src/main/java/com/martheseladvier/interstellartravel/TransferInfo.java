package com.martheseladvier.interstellartravel;

public class TransferInfo {
    String type = ""; //set to personal or HTC
    double cost = 0.0;

    public TransferInfo(String type, double cost){
    this.type = type;
    this.cost = cost;
    }
    public String getType(){
        return this.type;
    }

    public double getCost(){
        return this.cost;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

}
