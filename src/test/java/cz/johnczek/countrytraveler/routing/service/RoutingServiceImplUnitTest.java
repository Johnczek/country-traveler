package cz.johnczek.countrytraveler.routing.service;

import cz.johnczek.countrytraveler.core.exception.CountryHasNoNeighboursException;
import cz.johnczek.countrytraveler.core.exception.CountryNotFoundException;
import cz.johnczek.countrytraveler.core.exception.GraphNotReadyException;
import cz.johnczek.countrytraveler.core.exception.PathDoesNotExistException;
import cz.johnczek.countrytraveler.core.service.ShortestPathService;
import cz.johnczek.countrytraveler.country.dto.CountryInputDto;
import cz.johnczek.countrytraveler.country.graph.CountriesGraph;
import cz.johnczek.countrytraveler.country.service.CountryGraphService;
import cz.johnczek.countrytraveler.routing.response.ShortestPathResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoutingServiceImplUnitTest {

    private static final String DUMMY_SOURCE = "CZE";
    private static final String DUMMY_DESTINATION = "ESP";

    private static final String DUMMY_COUNTRY = "GET";

    @InjectMocks
    private RoutingServiceImpl instance;

    @Mock
    private CountryGraphService countryGraphService;

    @Mock
    private ShortestPathService shortestPathService;


    @Nested
    class FindShortestPathBetweenCountries {

        // TODO

        @Test
        void sameDestinationAndSource_oneListRoute() {
            ShortestPathResponse result = instance.findShortestPathBetweenCountries(DUMMY_SOURCE, DUMMY_SOURCE);

            assertThat(result).isNotNull();
            assertThat(result.getRoute()).hasSize(1);
            assertThat(result.getRoute().get(0)).isEqualTo(DUMMY_SOURCE);
        }

        @Test
        void graphNotReady_exceptionThrown() {
            when(countryGraphService.getGraph()).thenReturn(Optional.empty());

            assertThrows(GraphNotReadyException.class, () -> instance.findShortestPathBetweenCountries(DUMMY_SOURCE, DUMMY_DESTINATION));
        }

        @Test
        void sourceAndDestinationNotPresentInGraph_exceptionThrown() {
            when(countryGraphService.getGraph()).thenReturn(Optional.of(this.getEmptyGraph()));

            assertThrows(CountryNotFoundException.class, () -> instance.findShortestPathBetweenCountries(DUMMY_SOURCE, DUMMY_DESTINATION));
        }

        @Test
        void sourcePresentInGraphWithNoNeighbours_exceptionThrown() {

            CountryInputDto sourceInputDto = getCountryInput(DUMMY_SOURCE, null);

            when(countryGraphService.getGraph()).thenReturn(Optional.of(this.getGraphWithNodes(sourceInputDto, null)));

            assertThrows(CountryHasNoNeighboursException.class, () -> instance.findShortestPathBetweenCountries(DUMMY_SOURCE, DUMMY_DESTINATION));
        }

        @Test
        void destinationNotPresentInGraph_exceptionThrown() {

            CountryInputDto sourceInputDto = getCountryInput(DUMMY_SOURCE, List.of(DUMMY_COUNTRY));

            when(countryGraphService.getGraph()).thenReturn(Optional.of(this.getGraphWithNodes(sourceInputDto, null)));

            assertThrows(CountryNotFoundException.class, () -> instance.findShortestPathBetweenCountries(DUMMY_SOURCE, DUMMY_DESTINATION));
        }

        @Test
        void destinationPresentInGraphButWithoutNeighbours_exceptionThrown() {

            CountryInputDto sourceInputDto = getCountryInput(DUMMY_SOURCE, List.of(DUMMY_COUNTRY));
            CountryInputDto destinationInputDto = getCountryInput(DUMMY_DESTINATION, null);

            when(countryGraphService.getGraph()).thenReturn(Optional.of(this.getGraphWithNodes(sourceInputDto, destinationInputDto)));

            assertThrows(CountryHasNoNeighboursException.class, () -> instance.findShortestPathBetweenCountries(DUMMY_SOURCE, DUMMY_DESTINATION));
        }

        @Test
        void noPathFound_exceptionThrown() {

            CountryInputDto sourceInputDto = getCountryInput(DUMMY_SOURCE, List.of(DUMMY_COUNTRY));
            CountryInputDto destinationInputDto = getCountryInput(DUMMY_DESTINATION, List.of(DUMMY_COUNTRY));

            when(countryGraphService.getGraph()).thenReturn(Optional.of(this.getGraphWithNodes(sourceInputDto, destinationInputDto)));
            when(shortestPathService.findShortestPathInGraph(any(), any())).thenReturn(Collections.emptyList());

            assertThrows(PathDoesNotExistException.class, () -> instance.findShortestPathBetweenCountries(DUMMY_SOURCE, DUMMY_DESTINATION));
        }

        @Test
        void pathFound_pathReturnedViaObject() {

            List<String> expectedResult = List.of(DUMMY_SOURCE, DUMMY_COUNTRY, DUMMY_DESTINATION);
            CountryInputDto sourceInputDto = getCountryInput(DUMMY_SOURCE, List.of(DUMMY_COUNTRY));
            CountryInputDto destinationInputDto = getCountryInput(DUMMY_DESTINATION, List.of(DUMMY_COUNTRY));

            when(countryGraphService.getGraph()).thenReturn(Optional.of(this.getGraphWithNodes(sourceInputDto, destinationInputDto)));
            when(shortestPathService.findShortestPathInGraph(any(), any())).thenReturn(expectedResult);

            ShortestPathResponse result = instance.findShortestPathBetweenCountries(DUMMY_SOURCE, DUMMY_DESTINATION);

            assertThat(result).isNotNull();
            assertThat(result.getRoute()).isNotNull().isEqualTo(expectedResult);
        }

        private CountryInputDto getCountryInput(String name, List<String> neighbours) {
            CountryInputDto result = new CountryInputDto();
            result.setName(name);
            result.setNeighbours(neighbours);

            return result;
        }

        private CountriesGraph getEmptyGraph() {
            return new CountriesGraph();
        }

        private CountriesGraph getGraphWithNodes(CountryInputDto source, CountryInputDto destination) {
            CountriesGraph graph = new CountriesGraph();

            if (source != null) {
                graph.addNode(source);
            }

            if (destination != null) {
                graph.addNode(destination);
            }

            return graph;
        }

    }


}