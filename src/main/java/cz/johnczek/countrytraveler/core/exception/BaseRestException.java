package cz.johnczek.countrytraveler.core.exception;

import lombok.Getter;

@Getter
public class BaseRestException extends RuntimeException {

    private final transient Object[] args;

    public BaseRestException(String message) {
        super(message);
        this.args = new Object[]{};
    }

    public BaseRestException(String message, Object[] args) {
        super(message);
        this.args = args;
    }

    public BaseRestException(String message, Throwable cause, Object[] args) {
        super(message, cause);
        this.args = args;
    }

    public BaseRestException(String message, Throwable cause) {
        super(message, cause);
        this.args = new Object[]{};
    }
}
