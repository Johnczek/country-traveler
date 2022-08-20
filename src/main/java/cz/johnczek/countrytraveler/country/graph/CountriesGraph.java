package cz.johnczek.countrytraveler.country.graph;

import cz.johnczek.countrytraveler.core.exception.CountryNotInCorrectFormatException;
import cz.johnczek.countrytraveler.country.dto.CountryInputDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class CountriesGraph implements Serializable {

    private final Map<String, CountryNode> countriesByName = new HashMap<>();

    public Optional<CountryNode> getCountryByNameOpt(String countryName) {
        return Optional.ofNullable(countryName).filter(StringUtils::isNotBlank).map(countriesByName::get);
    }

    public CountryNode getCountryByName(String countryName) {
        return countriesByName.get(countryName);
    }

    /**
     * Method adds new node to graph.
     * If node already exists in the graph, its neighbours are updated.
     * If node is not present in graph, new object is created.
     * All present neighbours are added/updated in graph as well.
     *
     * @param countryInputDto node we want to add
     * @throws CountryNotInCorrectFormatException if country object is not valid
     */
    public void addNode(CountryInputDto countryInputDto) {
        if (countryInputDto == null || StringUtils.isBlank(countryInputDto.getName())) {
            throw new CountryNotInCorrectFormatException();
        }

        CountryNode addedNode = getCountryByNameOrCreate(countryInputDto.getName());

        if (CollectionUtils.isEmpty(countryInputDto.getNeighbours())) {
            return;
        }

        countryInputDto.getNeighbours().forEach((String neighbourName) -> {
            CountryNode neighbourNode = getCountryByNameOrCreate(neighbourName);
            addedNode.addNeighbourIfNotPresent(neighbourNode);
            neighbourNode.addNeighbourIfNotPresent(addedNode);
        });
    }

    public CountryNode getCountryByNameOrCreate(String countryName) {
        return Optional.ofNullable(countriesByName.get(countryName))
                .orElseGet(() -> {
                    CountryNode newNode = new CountryNode(countryName);
                    countriesByName.put(countryName, newNode);

                    return newNode;
                });
    }
}
