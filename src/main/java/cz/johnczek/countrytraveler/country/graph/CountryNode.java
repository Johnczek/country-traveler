package cz.johnczek.countrytraveler.country.graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class CountryNode {

    private final String name;

    private final List<CountryNode> neighbours = new ArrayList<>();

    @Setter
    private boolean processed;

    @Setter
    private CountryNode previous;

    public CountryNode(String name) {
        this.name = name;
    }

    public void addNeighbourIfNotPresent(@NonNull CountryNode neighbour) {
        boolean neighbourAlreadyPresent = neighbours.stream()
                .map(CountryNode::getName).anyMatch(countryName -> countryName.equals(neighbour.getName()));

        if (neighbourAlreadyPresent) {
            return;
        }

        neighbours.add(neighbour);
    }
}
