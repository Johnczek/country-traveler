package cz.johnczek.countrytraveler.core.exception;

public class CountryNotInGraphException extends BaseBadRequestRestException {

    private static final String EXCEPTION_MESSAGE_CODE = "error.countriesGraph.country.notInGraph";

    public CountryNotInGraphException(String name) {
        super(EXCEPTION_MESSAGE_CODE, new Object[]{name});
    }

    public CountryNotInGraphException(String name, Throwable cause) {
        super(EXCEPTION_MESSAGE_CODE, cause, new Object[]{name});
    }
}
