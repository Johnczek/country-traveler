package cz.johnczek.countrytraveler.core.exception;

public class BaseBadRequestRestException extends BaseRestException {

    public BaseBadRequestRestException(String message) {
        super(message);
    }

    public BaseBadRequestRestException(String message, Object[] args) {
        super(message, args);
    }

    public BaseBadRequestRestException(String message, Throwable cause, Object[] args) {
        super(message, cause, args);
    }

    public BaseBadRequestRestException(String message, Throwable cause) {
        super(message, cause);
    }
}