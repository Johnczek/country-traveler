package cz.johnczek.countrytraveler.country.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CountryInputDto {

    @JsonProperty("cca3")
    private String name;

    @JsonProperty("borders")
    private List<String> neighbours = new ArrayList<>();
}
