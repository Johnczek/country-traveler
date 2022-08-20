package cz.johnczek.countrytraveler.core.runner;

import cz.johnczek.countrytraveler.core.parser.CountryInputParser;
import cz.johnczek.countrytraveler.country.dto.CountryInputDto;
import cz.johnczek.countrytraveler.country.service.CountryGraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Runner loads and maps graph data from given input file
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BaseStartupRunner implements ApplicationRunner {

    private final CountryInputParser countryInputParser;

    private final CountryGraphService countryGraphService;

    private final ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<CountryInputDto> countriesInput = countryInputParser.getCountries();
        if (CollectionUtils.isEmpty(countriesInput)) {
            log.error("No countries returned from source, returning");

            SpringApplication.exit(applicationContext);
        }

        log.info("Creating graph for {} nodes", countriesInput.size());
        countryGraphService.saveGraphData(countriesInput);
        log.info("Graph successfully created, application is ready to accept requests");

    }
}
