package cz.johnczek.countrytraveler.core.exception;

public class BaseRestNotFoundException extends BaseRestException {

    public BaseRestNotFoundException(String message) {
        super(message);
    }

    public BaseRestNotFoundException(String message, Object[] args) {
        super(message, args);
    }

    public BaseRestNotFoundException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public BaseRestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
