package cz.johnczek.countrytraveler.routing.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class ShortestPathResponse {

    private List<String> route;
}
