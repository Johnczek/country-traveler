package cz.johnczek.countrytraveler.core.exception;

public class CountryNotFoundException extends BaseInternalServerErrorRestException {

    private static final String EXCEPTION_MESSAGE_CODE = "error.countriesGraph.country.name.notFund";

    public CountryNotFoundException(String name) {
        super(EXCEPTION_MESSAGE_CODE, new Object[]{name});
    }

    public CountryNotFoundException(String name, Throwable cause) {
        super(EXCEPTION_MESSAGE_CODE, cause, new Object[]{name});
    }
}
