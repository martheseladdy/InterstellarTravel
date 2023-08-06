package com.martheseladvier.interstellartravel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Route implements IRoute{
@Autowired
IQueryDatabase queryDatabase;
    Stack<String> route = null;
    int routeDistance = 0;
    //could implement a cache of source accelerators - significantly reduces the cons of the shortestRoute method

    public void shortestRoute(Accelerator from, Accelerator to){
        List<Accelerator> accelerators =  queryDatabase.getAllAccelerators();
        Accelerator focus;
        List<Connection> connections;
        Map<String,String> previousConnection = new HashMap<>();
        Map<String,Boolean> visited = new HashMap<>();
        Map<String,Integer> distances = new HashMap<>();

        for(Accelerator accel : accelerators){
            if (accel.getId().equalsIgnoreCase(from.getId())){
                focus = accel;
                connections = focus.getConnections();
                for(Connection conn: connections){
                    visited.put(conn.getId(), false);
                    previousConnection.put(conn.getId(), null);
                }
                distances.put(focus.getId(), 0);

                for(int i = 0; i < visited.size(); i++){
                    int shortestDistance = 999;
                    for(Connection conn : connections){
                        if(conn.getDistance() <= shortestDistance && !visited.get(conn.getId())){
                            shortestDistance = conn.getDistance();
                            focus = conn; //when this stops looping, the next focus accelerator will be the next connection with the shortest distance
                        }
                    }
                    visited.put(focus.getId(), true);

                    List<Connection> conns = focus.getConnections();
                    for(Connection conn : conns){
                        if(!visited.get(conn.getId())){
                            int contenderDistance = distances.get(focus.getId()) + conn.getDistance();
                            if(contenderDistance < distances.get(conn.getId())){
                                distances.put(conn.getId(), contenderDistance);
                                previousConnection.put(conn.getId(), focus.getId());
                            }
                        }
                    }
                }
            }
        }

        routeDistance = distances.get(to.getId());

        String next = to.getId();
        Stack<String> calculatedRoute = new Stack();

        while(next != null){
            calculatedRoute.push(next);
            next = previousConnection.get(next);
        }

        route = calculatedRoute;
    }

    public List<String> getRoute(){
        List<String> routeAccelerators = new ArrayList<>();
        for(String acceleratorId : route){
            routeAccelerators.add(route.pop());
        }
        return routeAccelerators;
    }

    public double getCost(){
        double cost = routeDistance * 0.45;
        return cost;
    }


}
