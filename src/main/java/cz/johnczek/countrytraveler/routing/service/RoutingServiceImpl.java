package cz.johnczek.countrytraveler.routing.service;

import cz.johnczek.countrytraveler.core.exception.CountryHasNoNeighboursException;
import cz.johnczek.countrytraveler.core.exception.CountryNotFoundException;
import cz.johnczek.countrytraveler.core.exception.GraphNotReadyException;
import cz.johnczek.countrytraveler.core.exception.PathDoesNotExistException;
import cz.johnczek.countrytraveler.core.service.ShortestPathService;
import cz.johnczek.countrytraveler.country.graph.CountriesGraph;
import cz.johnczek.countrytraveler.country.graph.CountryNode;
import cz.johnczek.countrytraveler.country.service.CountryGraphService;
import cz.johnczek.countrytraveler.routing.response.ShortestPathResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoutingServiceImpl implements RoutingService {

    private final CountryGraphService countryGraphService;

    private final ShortestPathService shortestPathService;

    /**
     * Method finds the shortest path between two countries
     *
     * @param sourceCountryName name of source country
     * @param destinationCountryName name of destination country
     * @return object holding shortest path for given input, in case of same source and destination, returns singleton list path
     * @throws GraphNotReadyException in case that graph is not ready yet
     * @throws CountryNotFoundException if any of these two countries can not be resolved by name
     * @throws CountryHasNoNeighboursException if any of there tro countries has no neighbours
     */
    @Override
    public ShortestPathResponse findShortestPathBetweenCountries(@NonNull String sourceCountryName, @NonNull String destinationCountryName) {

        if (sourceCountryName.equals(destinationCountryName)) {
            return ShortestPathResponse.builder().route(List.of(sourceCountryName)).build();
        }

        CountriesGraph graph = countryGraphService.getGraph().orElseThrow(() -> {
            log.error("Request failed, graph is not ready yet");

            return new GraphNotReadyException();
        });

        validateInput(sourceCountryName, destinationCountryName, graph);

        List<String> shortestPath = shortestPathService.findShortestPathInGraph(
                graph.getCountryByName(sourceCountryName), graph.getCountryByName(destinationCountryName));

        if (CollectionUtils.isEmpty(shortestPath)) {
            throw new PathDoesNotExistException();
        }

        return ShortestPathResponse.builder()
                .route(shortestPath)
                .build();
    }

    private void validateInput(
            @NonNull String sourceCountryName, @NonNull String destinationCountryName, @NonNull CountriesGraph graph) {


        CountryNode sourceNode = graph.getCountryByNameOpt(sourceCountryName)
                .orElseThrow(() -> {
                    log.error("Country with code {} was not found", sourceCountryName);

                    throw new CountryNotFoundException(sourceCountryName);
                });

        if (CollectionUtils.isEmpty(sourceNode.getNeighbours())) {
            throw new CountryHasNoNeighboursException(sourceNode.getName());
        }

        CountryNode destinationNode = graph.getCountryByNameOpt(destinationCountryName)
                .orElseThrow(() -> {
                    log.error("Country with code {} was not found", destinationCountryName);

                    throw new CountryNotFoundException(destinationCountryName);
                });

        if (CollectionUtils.isEmpty(destinationNode.getNeighbours())) {
            throw new CountryHasNoNeighboursException(destinationNode.getName());
        }
    }
}
