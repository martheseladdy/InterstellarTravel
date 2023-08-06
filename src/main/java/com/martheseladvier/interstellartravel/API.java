package com.martheseladvier.interstellartravel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/home")
public class API {
    //http://localhost:8080/swagger-ui/index.html
    @Autowired
    IQueryDatabase queryDatabase;
    @Autowired
    IRoute route;
    @Autowired
    ITransferCost transferCost;

    @Operation(
            summary = "Calculates the cheapest transport type for the specified journey.",
            description = "Calculates the cost of a one-way transfer for a personal transport or UTS transport. Returns the cheapest transport method, and the calculated journey cost. Can optionally take passenger number, and parking days."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Your request is being processed."
    )

    @GetMapping("/transport/{distance}")
    public String getCheapestTransfer(
        @PathVariable int distance,
        @Parameter(description = "Number of passengers") @RequestParam(required = false) int passengers,
        @Parameter(description = "Number of parking days") @RequestParam(required = false) int parking) {

        TransferInfo info = transferCost.cheapestTransfer(distance, passengers, parking);

        return "The cheapest transportation method for your journey is " + info.getType() + " and comes to £" + info.getCost() +".\n\nThank you for using our service, have a lovely trip.";
        }

    @Operation(
            summary = "Fetch list of accelerators.",
            description = "Returns information of all accelerators and their connections."
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
            summary = "Fetch information for a specific accelerator.",
            description = "Return connections of a specific accelerator."
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
            summary = "Calculate cheapest journey.",
            description = "Evaluates shortest path between accelerator stations and returns the route."
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