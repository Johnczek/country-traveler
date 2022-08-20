package cz.johnczek.countrytraveler.country.graph;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class CountryNodeUnitTest {

    private static final String DUMMY_COUNTRY_NAME = "USA";
    private static final String DUMMY_COUNTRY_NAME2 = "CZE";

    @Nested
    class AddNeighbourIfNotPresent {

        @Test
        void neighbourNotPresent_neighbourAdded() {
            CountryNode node = getNode(DUMMY_COUNTRY_NAME);

            assertThat(node.getNeighbours()).isEmpty();
            node.addNeighbourIfNotPresent(getNode(DUMMY_COUNTRY_NAME2));
            assertThat(node.getNeighbours()).hasSize(1);
            assertThat(node.getNeighbours().get(0).getName()).isEqualTo(DUMMY_COUNTRY_NAME2);
        }

        @Test
        void neighbourAlreadyPresent_noNeighbourAdded() {
            CountryNode node = getNode(DUMMY_COUNTRY_NAME);

            node.addNeighbourIfNotPresent(getNode(DUMMY_COUNTRY_NAME2));
            assertThat(node.getNeighbours()).hasSize(1);
            node.addNeighbourIfNotPresent(getNode(DUMMY_COUNTRY_NAME2));
            assertThat(node.getNeighbours()).hasSize(1);
        }
    }

    private CountryNode getNode(String name) {
        return new CountryNode(name);
    }
}