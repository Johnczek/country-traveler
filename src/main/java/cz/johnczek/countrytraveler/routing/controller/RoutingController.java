package cz.johnczek.countrytraveler.routing.controller;

import cz.johnczek.countrytraveler.routing.response.ShortestPathResponse;
import cz.johnczek.countrytraveler.routing.service.RoutingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(value = "routing", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RoutingController {

    private final RoutingService routingService;

    @Operation(summary = "Method finds shortest path in graph between two counries by given country codes")
    @ApiResponse(responseCode = "200", description = "Dto holding list of country codes on path. In case of same source and destination, singleton list is returned.")
    @ApiResponse(responseCode = "400", description = "In case if its unable to get path, for example if one of the country has no neighbours or if there is no path between these two countries.")
    @ApiResponse(responseCode = "404", description = "In case any of 2 input countries is not present in graph")
    @ApiResponse(responseCode = "500", description = "In case of server internal error")
    @ApiResponse(responseCode = "503", description = "If application is not ready to accept requests yet")
    @GetMapping("/{source}/{destination}")
    public ResponseEntity<ShortestPathResponse> getRoutingBetweenCountries(
            @Parameter(description="cca3 code of source country") @PathVariable String source,
            @Parameter(description="cca3 code of destination country") @PathVariable String destination) {

        if (StringUtils.isBlank(source) || StringUtils.isBlank(destination)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(routingService.findShortestPathBetweenCountries(source, destination), HttpStatus.OK);
    }
}
