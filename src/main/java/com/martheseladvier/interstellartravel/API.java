package com.martheseladvier.interstellartravel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home")
public class API {
    //http://localhost:8080/swagger-ui/index.html
  //  @Operation(
 //           summary = "Say hi, API!",
 //           description = "test API endpoint to ensure swagger/api documentation is correctly configured"
//    )
 //   @ApiResponse(
 //           responseCode = "200",
 //           description = "Everything looks good to me, pal."
 //   )
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, world!";
    }
}