package com.martheseladvier.interstellartravel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/UTS")
public class AcceleratorAPI {
    //http://localhost:8080/swagger-ui/index.html
    @Autowired
    IQueryDatabase queryDatabase;
    @Autowired
    IRoute route;

    @Operation(
            summary = "View a list of all accelerators.",
            description = "Returns ID, name, and connections (each with name and distance in hu) of all accelerators."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Your request is being processed."
    )

    @GetMapping("/accelerators")
    public String getAccelerators(){
        String response = "";
        List<Accelerator> accelerators = queryDatabase.getAllAccelerators();
        for(Accelerator acc : accelerators){
            response += "Accelerator: \nid: " +acc.getId() + "\nname: " + acc.getName() + "\nConnections: ";
            List<Connection> connections = acc.getConnections();
            for(Connection conn : connections){
                response+="\n   " + conn.getName() + ": " + conn.getDistance() + " hu";
            }
        }

        response += "\n\nThank you for using our service, have a lovely trip";

        return response;
    }

    @Operation(
            summary = "View information for a specific accelerator.",
            description = "See a specific accelerator's ID, name, and connections (by name and distance in hu)."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Your request is being processed."
    )

    @GetMapping("/accelerators/{acceleratorID}")
    public String getAcceleratorByID(
            @PathVariable String acceleratorID){

        Accelerator accelerator = queryDatabase.getAccelerator(acceleratorID);

        String response = "Accelerator: \nid: " +accelerator.getId() + "\nname: " + accelerator.getName() + "\nConnections: ";
        List<Connection> connections = accelerator.getConnections();
        for(Connection conn : connections){
            response+="\n   " + conn.getName() + ": " + conn.getDistance() + " hu";
        }

        response += "\n\nThank you for using our service, have a lovely trip.";

        return response;
    }

    @Operation(
            summary = "Calculate cheapest journey between accelerators.",
            description = "Evaluates the shortest path between accelerator stations and returns the route."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Your request is being processed."
    )
    @GetMapping("/accelerators/{acceleratorID}/to/{targetAcceleratorID}")

    public String getCheapestRoute(
            @PathVariable String acceleratorID,
            @PathVariable String targetAcceleratorID){
        Accelerator from = queryDatabase.getAccelerator(acceleratorID);
        Accelerator to = queryDatabase.getAccelerator(targetAcceleratorID);

        List<String> routeAccelerators = route.getRoute(from, to);
        String response = "START > ";
        for(String accelerator : routeAccelerators){
            response += accelerator + " > ";
        }
        response += "FINISH\n\n.";
        response += "The cost of this journey is £" + route.getCost() + "\n\nThank you for using our service, have a lovely trip.";

        return response;
    }

}