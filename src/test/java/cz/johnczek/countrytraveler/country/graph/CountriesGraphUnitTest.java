package cz.johnczek.countrytraveler.country.graph;

import cz.johnczek.countrytraveler.country.dto.CountryInputDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CountriesGraphUnitTest {

    private static final String DUMMY_COUNTRY_NAME = "USA";
    private static final String DUMMY_COUNTRY_NAME2 = "CZE";

    @Nested
    class GetCountryByNameOrCreate {

        @Test
        void countryNotPresent_presentAfterAddition() {
            CountriesGraph graph = new CountriesGraph();

            assertThat(graph.getCountryByName(DUMMY_COUNTRY_NAME)).isNull();

            graph.addNode(getCountryInput(DUMMY_COUNTRY_NAME, null));
            assertThat(graph.getCountryByName(DUMMY_COUNTRY_NAME)).isNotNull();
            assertThat(graph.getCountryByName(DUMMY_COUNTRY_NAME).getName()).isEqualTo(DUMMY_COUNTRY_NAME);
        }

        @Test
        void countryAlreadyPresent_countryReturned() {
            CountriesGraph graph = new CountriesGraph();

            graph.addNode(getCountryInput(DUMMY_COUNTRY_NAME, null));
            assertThat(graph.getCountryByName(DUMMY_COUNTRY_NAME)).isNotNull();
            assertThat(graph.getCountryByName(DUMMY_COUNTRY_NAME).getName()).isEqualTo(DUMMY_COUNTRY_NAME);

            graph.addNode(getCountryInput(DUMMY_COUNTRY_NAME, null));
            assertThat(graph.getCountryByName(DUMMY_COUNTRY_NAME).getName()).isEqualTo(DUMMY_COUNTRY_NAME);
        }
    }

    @Nested
    class GetCountryByNameOpt {

        @Test
        void countryNotPresent_optionalEmpty() {
            CountriesGraph graph = new CountriesGraph();

            assertThat(graph.getCountryByNameOpt(DUMMY_COUNTRY_NAME)).isEmpty();
        }

        @Test
        void countryPresent_countryReturned() {
            CountriesGraph graph = new CountriesGraph();
            graph.addNode(getCountryInput(DUMMY_COUNTRY_NAME, null));

            assertThat(graph.getCountryByNameOpt(DUMMY_COUNTRY_NAME)).isNotEmpty();
            assertThat(graph.getCountryByNameOpt(DUMMY_COUNTRY_NAME).get().getName()).isEqualTo(DUMMY_COUNTRY_NAME);
        }
    }

    @Nested
    class GetCountryByName {

        @Test
        void countryNotPresent_null() {
            CountriesGraph graph = new CountriesGraph();

            assertThat(graph.getCountryByName(DUMMY_COUNTRY_NAME)).isNull();
        }

        @Test
        void countryPresent_countryReturned() {
            CountriesGraph graph = new CountriesGraph();
            graph.addNode(getCountryInput(DUMMY_COUNTRY_NAME, null));

            assertThat(graph.getCountryByName(DUMMY_COUNTRY_NAME)).isNotNull();
            assertThat(graph.getCountryByName(DUMMY_COUNTRY_NAME).getName()).isEqualTo(DUMMY_COUNTRY_NAME);
        }
    }

    @Nested
    class AddNode {

        // TODO
    }

    private CountryInputDto getCountryInput(String name, List<String> neighbours) {
        CountryInputDto result = new CountryInputDto();
        result.setName(name);
        result.setNeighbours(neighbours);

        return result;
    }
}