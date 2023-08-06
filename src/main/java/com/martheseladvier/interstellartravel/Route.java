package com.martheseladvier.interstellartravel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class Route implements IRoute{
@Autowired
IQueryDatabase queryDatabase;
    Map<String, String> previousAccelerator;
    Map<String, Integer> shortestDistanceToEachAccelerator;
    Accelerator toAccelerator;

    //could implement a cache of start accelerators - significantly reduces the cons of the shortestRoute method

    public void shortestRoute(Accelerator from) throws Exception{
        try{
        List<Accelerator> accelerators =  queryDatabase.getAllAccelerators();
        Accelerator focus = from;
        Accelerator consider;
        Map<String, String> previousAccelerator = new HashMap<>();
        Map<String, Integer> shortestDistanceToEachAccelerator = new HashMap<>();
        int shortestDistance;
        LinkedList<String> unvisitedAccelerators = new LinkedList<>();

        //initialising
        for(Accelerator accelerator : accelerators){
            shortestDistanceToEachAccelerator.put(accelerator.getId(), Integer.MAX_VALUE);
            previousAccelerator.put(accelerator.getId(), null);
            unvisitedAccelerators.add(accelerator.getId());
        }
        shortestDistanceToEachAccelerator.put(from.getId(), 0);

        while(!unvisitedAccelerators.isEmpty()){

                shortestDistance = Integer.MAX_VALUE;
                //find closest unvisited/next focus
                for(String acceleratorId : unvisitedAccelerators){
                    consider = queryDatabase.getAccelerator(acceleratorId);
                    int considerDistance = shortestDistanceToEachAccelerator.get(consider.getId());
                    if(considerDistance < shortestDistance){
                        shortestDistance = considerDistance;
                        focus = consider;

                    }
                }
                if(focus == null){
                  break;
                }
                unvisitedAccelerators.remove(focus.getId());
                //find closet connection to current focus
                for(Connection connection : focus.getConnections()){
                    int considerDistance = shortestDistanceToEachAccelerator.get(focus.getId()) + connection.getDistance();
                    if(considerDistance < shortestDistanceToEachAccelerator.get(connection.getId())){
                        shortestDistanceToEachAccelerator.put(connection.getId(), considerDistance);
                        previousAccelerator.put(connection.getId(), focus.getId());
                    }
                }

        }

        this.previousAccelerator = previousAccelerator;
        this.shortestDistanceToEachAccelerator = shortestDistanceToEachAccelerator;

        }
        catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

    public List<String> getRoute(Accelerator from, Accelerator to) throws Exception{
        try {
            this.toAccelerator = to;
            shortestRoute(from);
            // Reconstruct route from finishing accelerator backwards
            List<String> route = new ArrayList<>();
            String focusId = to.getId();
            System.out.println(focusId);
            while (focusId != null) {
                route.add(focusId);
                focusId = previousAccelerator.get(focusId);
            }
            Collections.reverse(route);

            return route;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    public double getCost() throws Exception{
        try {
            double cost = shortestDistanceToEachAccelerator.get(toAccelerator.getId()) * 0.10;

            BigDecimal currencyFormat = new BigDecimal(cost).setScale(2, RoundingMode.HALF_UP);
            cost = currencyFormat.doubleValue();
            return cost;
        }
        catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

}
