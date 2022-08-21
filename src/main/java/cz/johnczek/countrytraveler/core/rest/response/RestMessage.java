package cz.johnczek.countrytraveler.core.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RestMessage {

    private RestMessageTypeEnum messageType;

    private String value;

    private String messageCode;
}
