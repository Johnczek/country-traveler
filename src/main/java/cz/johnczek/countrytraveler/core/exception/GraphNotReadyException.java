package cz.johnczek.countrytraveler.core.exception;

public class GraphNotReadyException extends BaseServiceUnavailableErrorRestException {

    private static final String EXCEPTION_MESSAGE_CODE = "error.graph.notReady";

    public GraphNotReadyException() {
        super(EXCEPTION_MESSAGE_CODE, new Object[]{});
    }

    public GraphNotReadyException(Throwable cause) {
        super(EXCEPTION_MESSAGE_CODE, cause, new Object[]{});
    }
}
