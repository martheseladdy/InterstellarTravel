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
            @Parameter(description = "Number of parking days") @RequestParam(required = false) Integer parking) throws Exception {
        try {
            TransferInfo info = transferCost.cheapestTransfer(distance, passengers, parking);
            String currency = format("%.2f", info.getCost());
//This is decorative and only for demonstration purposes. For a true API, or an API/package intended for real use and not skills demonstration, this would simply return a TransferInfo object
            return "The cheapest transportation method for your journey is " + info.getType() + " and comes to £" + currency + "\n\nThank you for using our service, have a lovely trip.";
        } catch (Exception e) {
            return "Error: " + e.toString();
        }
    }

}