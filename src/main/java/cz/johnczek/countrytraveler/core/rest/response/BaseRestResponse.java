package cz.johnczek.countrytraveler.core.rest.response;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
public class BaseRestResponse {

    private Set<RestMessage> messages = new LinkedHashSet<>();

    public BaseRestResponse(@NonNull Set<RestMessage> messages) {
        this.messages = new LinkedHashSet<>(messages);
    }

    public void addMessage(@NonNull RestMessage message) {
        messages.add(message);
    }

    public Set<RestMessage> getMessages() {
        return new LinkedHashSet<>(messages);
    }
}
