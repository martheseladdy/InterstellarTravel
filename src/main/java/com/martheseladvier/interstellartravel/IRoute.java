package com.martheseladvier.interstellartravel;

import java.util.List;

public interface IRoute {
    public List<Accelerator> shortestRoute(Accelerator from, Accelerator to);
    public List<Accelerator> getRoute();
    public double getCost();

}
