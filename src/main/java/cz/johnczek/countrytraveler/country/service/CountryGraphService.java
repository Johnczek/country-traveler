package cz.johnczek.countrytraveler.country.service;

import cz.johnczek.countrytraveler.core.exception.CountryNotInCorrectFormatException;
import cz.johnczek.countrytraveler.country.dto.CountryInputDto;
import cz.johnczek.countrytraveler.country.graph.CountriesGraph;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface CountryGraphService {

    /**
     * Method saves mapped data from input file.
     *
     * @param countryInputDtos mapped data to be saved
     */
    void saveGraphData(@NonNull List<CountryInputDto> countryInputDtos);

    /**
     * @return graph from input data, optional empty in case it was impossible to construct graph
     * @throws CountryNotInCorrectFormatException if any country from input data is not in correct format
     */
    Optional<CountriesGraph> getGraph();
}
