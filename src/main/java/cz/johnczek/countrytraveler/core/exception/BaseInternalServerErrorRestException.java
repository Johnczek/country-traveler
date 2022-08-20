package cz.johnczek.countrytraveler.core.exception;

public class BaseInternalServerErrorRestException extends BaseRestException {

    public BaseInternalServerErrorRestException(String message) {
        super(message);
    }

    public BaseInternalServerErrorRestException(String message, Object[] args) {
        super(message, args);
    }

    public BaseInternalServerErrorRestException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public BaseInternalServerErrorRestException(String message, Throwable cause) {
        super(message, cause);
    }
}