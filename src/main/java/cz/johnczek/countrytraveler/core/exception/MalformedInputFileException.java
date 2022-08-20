package cz.johnczek.countrytraveler.core.exception;

public class MalformedInputFileException extends BaseInternalServerErrorRestException {

    private static final String EXCEPTION_MESSAGE_CODE = "error.inputFile.malformed";

    public MalformedInputFileException() {
        super(EXCEPTION_MESSAGE_CODE, new Object[]{});
    }

    public MalformedInputFileException(Throwable cause) {
        super(EXCEPTION_MESSAGE_CODE, cause, new Object[]{});
    }
}
