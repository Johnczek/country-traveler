package cz.johnczek.countrytraveler.core.exception;

public class CountryHasNoNeighboursException extends BaseBadRequestRestException {

    private static final String EXCEPTION_MESSAGE_CODE = "error.countriesGraph.country.noNeighbours";

    public CountryHasNoNeighboursException(String name) {
        super(EXCEPTION_MESSAGE_CODE, new Object[]{name});
    }

    public CountryHasNoNeighboursException(String name, Throwable cause) {
        super(EXCEPTION_MESSAGE_CODE, cause, new Object[]{name});
    }
}
