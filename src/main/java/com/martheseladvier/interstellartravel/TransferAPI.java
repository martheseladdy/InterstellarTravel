package com.martheseladvier.interstellartravel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/UTS")
public class TransferAPI {
    //http://localhost:8080/swagger-ui/index.html
    @Autowired
    ITransferCost transferCost;

    @Operation(
            summary = "See the cheapest transport type for the specified journey.",
            description = "Calculates the cost of a one-way transfer for a personal transport or HTS transport. Returns the cheapest transport method, and the calculated journey cost. Can optionally take passenger number, and parking days."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Your request is being processed."
    )

    @GetMapping("/transport/{distance}")
    public String getCheapestTransfer(
            @PathVariable int distance,
            @Parameter(description = "Number of passengers") @RequestParam(required = false) Integer passengers,
            @Parameter(description = "Number of parking days") @RequestParam(required = false) Integer parking) {

        TransferInfo info = transferCost.cheapestTransfer(distance, passengers, parking);

        return "The cheapest transportation method for your journey is " + info.getType() + " and comes to £" + info.getCost() +".\n\nThank you for using our service, have a lovely trip.";
    }


}