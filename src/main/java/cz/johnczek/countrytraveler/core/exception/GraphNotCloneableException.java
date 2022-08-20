package cz.johnczek.countrytraveler.core.exception;

public class GraphNotCloneableException extends BaseInternalServerErrorRestException {

    private static final String EXCEPTION_MESSAGE_CODE = "error.countriesGraph.notCloneable";

    public GraphNotCloneableException() {
        super(EXCEPTION_MESSAGE_CODE, new Object[]{});
    }

    public GraphNotCloneableException(Throwable cause) {
        super(EXCEPTION_MESSAGE_CODE, cause, new Object[]{});
    }
}
