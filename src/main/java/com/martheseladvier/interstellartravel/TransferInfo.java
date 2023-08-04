package com.martheseladvier.interstellartravel;

public class TransferInfo {
    String type = ""; //set to personal or HTC
    int distance = 0;
    Integer passengers = 0;
    Integer parkingDays = 0;
    double cost = 0.0;

    public TransferInfo(String type){
    this.type = type;
    }
    public TransferInfo(String type, int distance, Integer passengers, Integer parkingDays){
        this.type = type;
        this.distance = distance;
        this.passengers = passengers;
        this.parkingDays = parkingDays;

    }

    public TransferInfo(String type, int distance, Integer passengers){
        this.type = type;
        this.distance = distance;
        this.passengers = passengers;
        this.parkingDays = parkingDays;

    }
    public String getType(){
        return this.type;
    }

    public int getDistance(){
        return this.distance;
    }


    public Integer getPassengers(){
        return this.passengers;
    }

    public Integer getParkingDays(){
        return this.parkingDays;
    }

    public double getCost(){
        return this.cost;
    }


    public void setType(String type){
        this.type = type;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public void setPassengers(Integer passengers){
        this.passengers = passengers;
    }

    public void setParkingDays(Integer parkingDays){
        this.parkingDays = parkingDays;
    }
    public void setCost(double cost){
        this.cost = cost;
    }

}
