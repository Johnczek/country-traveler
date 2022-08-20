package cz.johnczek.countrytraveler.routing.controller;

import cz.johnczek.countrytraveler.routing.response.ShortestPathResponse;
import cz.johnczek.countrytraveler.routing.service.RoutingService;
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

    @GetMapping("/{source}/{destination}")
    public ResponseEntity<ShortestPathResponse> getRoutingBetweenCountries(@PathVariable String source, @PathVariable String destination) {

        if (StringUtils.isBlank(source) || StringUtils.isBlank(destination)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(routingService.findShortestPathBetweenCountries(source, destination), HttpStatus.OK);
    }
}
