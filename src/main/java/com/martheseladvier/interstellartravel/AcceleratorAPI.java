package com.martheseladvier.interstellartravel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;


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
    public String getAccelerators()throws Exception{
        try {
            String response = "";
            List<Accelerator> accelerators = queryDatabase.getAllAccelerators();
            //This is decorative and only for demonstration purposes. For a true API, or an API/package intended for real use and not skills demonstration, this would simply return a list of accelerators

            for (Accelerator acc : accelerators) {
                response += "\nAccelerator: \nid: " + acc.getId() + "\nname: " + acc.getName() + "\nConnections: ";
                List<Connection> connections = acc.getConnections();
                for (Connection conn : connections) {
                    response += "\n   " + conn.getName() + ": " + conn.getDistance() + " hu";
                }
            }

            response += "\n\nThank you for using our service, have a lovely trip";

            return response;
        }
        catch (Exception e) {
        return "Error: " + e.toString();
        }
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
            @PathVariable String acceleratorID) throws Exception{
        try {

            Accelerator accelerator = queryDatabase.getAccelerator(acceleratorID.toUpperCase());
            //This is decorative and only for demonstration purposes. For a true API, or an API/package intended for real use and not skills demonstration, this would simply return an Accelerator object

            String response = "Accelerator: \nid: " + accelerator.getId() + "\nname: " + accelerator.getName() + "\nConnections: ";
            List<Connection> connections = accelerator.getConnections();
            for (Connection conn : connections) {
                response += "\n   " + conn.getName() + ": " + conn.getDistance() + " hu";
            }

            response += "\n\nThank you for using our service, have a lovely trip.";

            return response;
        }
        catch (Exception e){
            return "Error: " + e.toString();
        }
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
            @PathVariable String targetAcceleratorID) throws Exception{
        try {
            Accelerator from = queryDatabase.getAccelerator(acceleratorID.toUpperCase());
            Accelerator to = queryDatabase.getAccelerator(targetAcceleratorID.toUpperCase());
            String response = "";
            List<String> routeAccelerators = null;

            //This is decorative and only for demonstration purposes. For a true API, or an API/package intended for real use and not skills demonstration, this would simply return a route list

            for(int i = 0; i < 2; i++){
                if(i == 0){
                    routeAccelerators = route.getRoute(from, to);
                    response += "Outbound:\n\n ";
                }
                else if (i == 1){
                    routeAccelerators = route.getRoute(to, from);
                    response += "Inbound:\n\n ";
                }
                response += "START > ";
                for (String accelerator : routeAccelerators) {
                    response += accelerator + " > ";
                }
                response += "FINISH\n\n";
                String currency = format("%.2f", route.getCost());
                response += "The cost of this journey is £" + currency + "\n\n";


            }
            response += "Thank you for using our service, have a lovely trip.";
            return response;

        }
        catch (Exception e){
            return "Error: " + e.toString();
        }
    }

}