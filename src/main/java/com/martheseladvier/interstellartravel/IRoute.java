package com.martheseladvier.interstellartravel;

import java.util.List;

public interface IRoute {
    public void shortestRoute(Accelerator from) throws Exception;
    public List<String> getRoute(Accelerator from, Accelerator to) throws Exception;
    public double getCost() throws Exception;

}
