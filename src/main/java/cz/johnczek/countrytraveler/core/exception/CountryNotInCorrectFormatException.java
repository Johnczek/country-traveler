package cz.johnczek.countrytraveler.core.exception;

public class CountryNotInCorrectFormatException extends BaseBadRequestRestException {

    private static final String EXCEPTION_MESSAGE_CODE = "error.countriesGraph.country.wrongFormat";

    public CountryNotInCorrectFormatException() {
        super(EXCEPTION_MESSAGE_CODE, new Object[]{});
    }

    public CountryNotInCorrectFormatException(Throwable cause) {
        super(EXCEPTION_MESSAGE_CODE, cause, new Object[]{});
    }
}
