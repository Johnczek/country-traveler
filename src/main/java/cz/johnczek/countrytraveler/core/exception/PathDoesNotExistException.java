package cz.johnczek.countrytraveler.core.exception;

public class PathDoesNotExistException extends BaseBadRequestRestException {

    private static final String EXCEPTION_MESSAGE_CODE = "error.countriesGraph.noPath";

    public PathDoesNotExistException() {
        super(EXCEPTION_MESSAGE_CODE, new Object[]{});
    }

    public PathDoesNotExistException(Throwable cause) {
        super(EXCEPTION_MESSAGE_CODE, cause, new Object[]{});
    }
}
