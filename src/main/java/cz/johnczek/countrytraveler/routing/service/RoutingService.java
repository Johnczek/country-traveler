package cz.johnczek.countrytraveler.routing.service;

import cz.johnczek.countrytraveler.routing.response.ShortestPathResponse;
import lombok.NonNull;

public interface RoutingService {

    ShortestPathResponse findShortestPathBetweenCountries(
            @NonNull String sourceCountryName, @NonNull String destinationCountryName);
}
