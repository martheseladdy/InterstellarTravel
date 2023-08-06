package com.martheseladvier.interstellartravel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RouteTest {
    Route route = new Route();
    public List<Accelerator> testShortestRoute(){

        List<Connection> connectionSol = new ArrayList<>(Arrays.asList(
                new Connection("RAN", "Ran", 100),
                new Connection("PRX", "Proxima", 90),
                new Connection("SIR", "Sirius", 100),
                new Connection("ARC", "Arrcturus", 200),
                new Connection("ALD", "Aldermain", 250)
        ));

        Accelerator acceleratorSol = new Accelerator("SOL", "Sol", connectionSol);


        List<Connection> connectionAls = new ArrayList<>(Arrays.asList(
                new Connection("ALT", "Altair", 1),
                new Connection("ALD", "Aldermain", 1)
        ));

        Accelerator acceleratorAls = new Accelerator("ALS", "Alshain", connectionAls);

        route.shortestRoute(acceleratorSol, acceleratorAls);

        List<String> expectedRoute = new ArrayList(Arrays.asList("SOL", "ARC", "DEN", "FOM", "ALS"));
        assertEquals(expectedRoute,route.getRoute());
        assertEquals(33.7,route.getCost());


        return null;
    }


}