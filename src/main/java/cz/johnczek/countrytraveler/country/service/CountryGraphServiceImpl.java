package cz.johnczek.countrytraveler.country.service;

import cz.johnczek.countrytraveler.country.dto.CountryInputDto;
import cz.johnczek.countrytraveler.country.graph.CountriesGraph;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryGraphServiceImpl implements CountryGraphService {

    private List<CountryInputDto> countryInputDtos;

    @Override
    public void saveGraphData(@NonNull List<CountryInputDto> countryInputDtos) {
        this.countryInputDtos = countryInputDtos;
    }

    @Override
    public Optional<CountriesGraph> getGraph() {

        CountriesGraph graph = new CountriesGraph();

        countryInputDtos.forEach((graph::addNode));

        return Optional.of(graph);
    }
}
