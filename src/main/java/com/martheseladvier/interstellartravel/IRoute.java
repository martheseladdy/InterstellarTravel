package com.martheseladvier.interstellartravel;

import java.util.List;

public interface IRoute {
    public void shortestRoute(Accelerator from);
    public List<String> getRoute(Accelerator from, Accelerator to);
    public double getCost();

}
