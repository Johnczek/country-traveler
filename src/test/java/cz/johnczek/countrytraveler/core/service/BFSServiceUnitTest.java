package cz.johnczek.countrytraveler.core.service;

import cz.johnczek.countrytraveler.country.dto.CountryInputDto;
import cz.johnczek.countrytraveler.country.graph.CountriesGraph;
import cz.johnczek.countrytraveler.country.graph.CountryNode;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class BFSServiceUnitTest {

    private static final String DUMMY_SOURCE_NAME = "CZE";
    private static final String DUMMY_DESTINATION_NAME = "ESP";
    private static final String DUMMY_COUNTRY1_NAME = "GER";
    private static final String DUMMY_COUNTRY2_NAME = "FRA";


    @InjectMocks
    private BFSService instance;

    @Nested
    class FindShortestPathInGraph {

        @Test
        void noPath_emptyList() {

            CountriesGraph graph = new CountriesGraph();
            graph.addNode(getCountryInput(DUMMY_SOURCE_NAME, List.of(DUMMY_COUNTRY1_NAME)));
            graph.addNode(getCountryInput(DUMMY_DESTINATION_NAME, List.of(DUMMY_COUNTRY2_NAME)));

            CountryNode sourceNode = graph.getCountryByName(DUMMY_SOURCE_NAME);
            CountryNode destinationNode = graph.getCountryByName(DUMMY_DESTINATION_NAME);

            assertThat(instance.findShortestPathInGraph(sourceNode, destinationNode)).isEmpty();
        }

        @Test
        void destAndSourceNeighbours_listOf2Size() {

            CountriesGraph graph = new CountriesGraph();
            graph.addNode(getCountryInput(DUMMY_SOURCE_NAME, List.of(DUMMY_DESTINATION_NAME)));
            graph.addNode(getCountryInput(DUMMY_DESTINATION_NAME, List.of(DUMMY_SOURCE_NAME)));

            CountryNode sourceNode = graph.getCountryByName(DUMMY_SOURCE_NAME);
            CountryNode destinationNode = graph.getCountryByName(DUMMY_DESTINATION_NAME);

            List<String> result = instance.findShortestPathInGraph(sourceNode, destinationNode);
            assertThat(result).hasSize(2);
            assertThat(result.get(0)).isEqualTo(DUMMY_SOURCE_NAME);
            assertThat(result.get(1)).isEqualTo(DUMMY_DESTINATION_NAME);
        }

        @Test
        void destAndSourceSeparatedByOneCountry_listOf3Size() {

            CountriesGraph graph = new CountriesGraph();
            graph.addNode(getCountryInput(DUMMY_SOURCE_NAME, List.of(DUMMY_COUNTRY1_NAME)));
            graph.addNode(getCountryInput(DUMMY_COUNTRY1_NAME, List.of(DUMMY_DESTINATION_NAME)));
            graph.addNode(getCountryInput(DUMMY_DESTINATION_NAME, List.of(DUMMY_COUNTRY1_NAME)));

            CountryNode sourceNode = graph.getCountryByName(DUMMY_SOURCE_NAME);
            CountryNode destinationNode = graph.getCountryByName(DUMMY_DESTINATION_NAME);

            List<String> result = instance.findShortestPathInGraph(sourceNode, destinationNode);
            assertThat(result).hasSize(3);
            assertThat(result.get(0)).isEqualTo(DUMMY_SOURCE_NAME);
            assertThat(result.get(1)).isEqualTo(DUMMY_COUNTRY1_NAME);
            assertThat(result.get(2)).isEqualTo(DUMMY_DESTINATION_NAME);
        }

        @Test
        void destAndSourceSeparatedByTwoCountries_listOf4Size() {

            CountriesGraph graph = new CountriesGraph();
            graph.addNode(getCountryInput(DUMMY_SOURCE_NAME, List.of(DUMMY_COUNTRY1_NAME)));
            graph.addNode(getCountryInput(DUMMY_COUNTRY1_NAME, List.of(DUMMY_COUNTRY2_NAME)));
            graph.addNode(getCountryInput(DUMMY_COUNTRY2_NAME, List.of(DUMMY_COUNTRY1_NAME)));
            graph.addNode(getCountryInput(DUMMY_DESTINATION_NAME, List.of(DUMMY_COUNTRY2_NAME)));

            CountryNode sourceNode = graph.getCountryByName(DUMMY_SOURCE_NAME);
            CountryNode destinationNode = graph.getCountryByName(DUMMY_DESTINATION_NAME);

            List<String> result = instance.findShortestPathInGraph(sourceNode, destinationNode);
            assertThat(result).hasSize(4);
            assertThat(result.get(0)).isEqualTo(DUMMY_SOURCE_NAME);
            assertThat(result.get(1)).isEqualTo(DUMMY_COUNTRY1_NAME);
            assertThat(result.get(2)).isEqualTo(DUMMY_COUNTRY2_NAME);
            assertThat(result.get(3)).isEqualTo(DUMMY_DESTINATION_NAME);
        }

        @Test
        void destAndSourceNeighboursWithConnectedOtherNeighbours_straightPath() {

            CountriesGraph graph = new CountriesGraph();
            graph.addNode(getCountryInput(DUMMY_SOURCE_NAME, List.of(DUMMY_COUNTRY1_NAME, DUMMY_DESTINATION_NAME)));
            graph.addNode(getCountryInput(DUMMY_COUNTRY1_NAME, List.of(DUMMY_COUNTRY2_NAME)));
            graph.addNode(getCountryInput(DUMMY_COUNTRY2_NAME, List.of(DUMMY_COUNTRY1_NAME)));
            graph.addNode(getCountryInput(DUMMY_DESTINATION_NAME, List.of(DUMMY_COUNTRY2_NAME, DUMMY_SOURCE_NAME)));

            CountryNode sourceNode = graph.getCountryByName(DUMMY_SOURCE_NAME);
            CountryNode destinationNode = graph.getCountryByName(DUMMY_DESTINATION_NAME);

            List<String> result = instance.findShortestPathInGraph(sourceNode, destinationNode);
            assertThat(result).hasSize(2);
            assertThat(result.get(0)).isEqualTo(DUMMY_SOURCE_NAME);
            assertThat(result.get(1)).isEqualTo(DUMMY_DESTINATION_NAME);
        }

    }

    private CountryInputDto getCountryInput(String name, List<String> neighbours) {
        CountryInputDto result = new CountryInputDto();
        result.setName(name);
        result.setNeighbours(neighbours);

        return result;
    }
}