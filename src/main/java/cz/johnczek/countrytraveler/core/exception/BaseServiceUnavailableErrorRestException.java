package cz.johnczek.countrytraveler.core.exception;

public class BaseServiceUnavailableErrorRestException extends BaseRestException {

    public BaseServiceUnavailableErrorRestException(String message) {
        super(message);
    }

    public BaseServiceUnavailableErrorRestException(String message, Object[] args) {
        super(message, args);
    }

    public BaseServiceUnavailableErrorRestException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public BaseServiceUnavailableErrorRestException(String message, Throwable cause) {
        super(message, cause);
    }
}