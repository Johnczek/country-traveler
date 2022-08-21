package cz.johnczek.countrytraveler.core.exception;

import cz.johnczek.countrytraveler.core.rest.response.BaseRestResponse;
import cz.johnczek.countrytraveler.core.rest.response.RestMessage;
import cz.johnczek.countrytraveler.core.rest.response.RestMessageTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@Order(1)
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DEFAULT_MESSAGE = "V aplikaci nastala chyba";

    private final MessageSource messageSource;

    @ExceptionHandler(BaseBadRequestRestException.class)
    public ResponseEntity<BaseRestResponse> handleBadRequest(BaseRestException exception) {
        return prepareResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseServiceUnavailableErrorRestException.class)
    public ResponseEntity<BaseRestResponse> handleServiceUnavailable(BaseRestException exception) {
        return prepareResponseEntity(exception, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BaseRestNotFoundException.class)
    public ResponseEntity<BaseRestResponse> handleNotFound(BaseRestException exception) {
        return prepareResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BaseInternalServerErrorRestException.class)
    public ResponseEntity<BaseRestResponse> handleInternalError(BaseRestException exception) {
        return prepareResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseRestException.class)
    public ResponseEntity<String> handleDefault(Throwable t) {
        return new ResponseEntity<>(t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Generates response entity from exception and given status.
     *
     * @param exception exception to be parsed into response
     * @param status    status of response
     * @return response entity containing list of error messages and given status code
     */
    private ResponseEntity<BaseRestResponse> prepareResponseEntity(BaseRestException exception, HttpStatus status) {
        BaseRestResponse response = new BaseRestResponse();
        response.addMessage(getMessage(exception.getMessage(), exception.getArgs()));

        return new ResponseEntity<>(response, status);
    }

    /**
     * Method generates RestMessage of type {@code RestMessageType.ERROR} for exception.
     *
     * @param messageCode messageCode for which will be found properties message
     * @param args        if properties message has arguments, these arguments will be used to fill them
     * @return formatted error message
     */
    private RestMessage getMessage(String messageCode, Object[] args) {
        return RestMessage.builder()
                .value(messageSource.getMessage(messageCode, args, DEFAULT_MESSAGE, Locale.getDefault()))
                .messageCode(messageCode)
                .messageType(RestMessageTypeEnum.ERROR)
                .build();
    }
}