package com.martheseladvier.interstellartravel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/home")
public class API {
    //http://localhost:8080/swagger-ui/index.html

    @Operation(
            summary = "Calculates the cheapest transport type for the specified journey.",
            description = "Calculates the cost of a one-way transfer for a personal transport or UTS transport. Returns the cheapest transport method, and the calculated journey cost. Can optionally take passenger number, and parking days."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Your request is being processed."
    )

    @GetMapping("/trasport/{distance}")
    public String getCheapestTransfer(
        @PathVariable int distance,
        @Parameter(description = "Number of passengers") @RequestParam(required = false) int passengers,
        @Parameter(description = "Number of parking days") @RequestParam(required = false) int parking) {
        //call method

        return "Your costs here :)";
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
        //call method

        return "All accelerators";
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
    public String getAcceleratorByID(){
        //call method
        return "Single accelerator information";
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
            @PathVariable int acceleratorID,
            @PathVariable int targetAcceleratorID){
        //call method

        return "Your costs here :)";
    }

}