package com.martheseladvier.interstellartravel;

import java.util.List;

public interface IRoute {
    void shortestRoute(Accelerator from) throws Exception;
    List<String> getRoute(Accelerator from, Accelerator to) throws Exception;
    double getCost() throws Exception;

}
