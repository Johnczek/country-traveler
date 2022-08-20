package cz.johnczek.countrytraveler.core.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.johnczek.countrytraveler.core.exception.MalformedInputFileException;
import cz.johnczek.countrytraveler.country.dto.CountryInputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static org.springframework.util.StreamUtils.copyToByteArray;

@Slf4j
@Component
@RequiredArgsConstructor
public class CountryInputParser {

    private static final String SOURCE_FILE_DEST = "assets/countries.json";

    private final ObjectMapper objectMapper;

    public List<CountryInputDto> getCountries() {

        try {
            byte[] fileContent = copyToByteArray(new
                    ClassPathResource(SOURCE_FILE_DEST).getInputStream());

            return objectMapper.readValue(fileContent, new TypeReference<>(){});
        } catch (IOException e) {
            log.error("Unable to read json input");
            throw new MalformedInputFileException();
        }
    }
}
