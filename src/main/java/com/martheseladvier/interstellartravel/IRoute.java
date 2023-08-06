package com.martheseladvier.interstellartravel;

import java.util.List;

public interface IRoute {
    public void shortestRoute(Accelerator from, Accelerator to);
    public List<String> getRoute();
    public double getCost();

}
