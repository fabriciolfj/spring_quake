package br.com.game.log.quake.api.exceptions;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handlerMaxSizeFileException(final MaxUploadSizeExceededException e,final WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var httpHeaders = new HttpHeaders();
        var error = createStandarErrorBuilder(HttpStatus.BAD_REQUEST, StandardErrorType.MAX_SIZE_FILE, e.getMessage());
        return handleExceptionInternal(e, error, httpHeaders, status, request);
    }

    @ExceptionHandler(FileInvalidException.class)
    public ResponseEntity<?> handlerFileInvalidException(final FileInvalidException e,final WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var httpHeaders = new HttpHeaders();
        var error = createStandarErrorBuilder(HttpStatus.BAD_REQUEST, StandardErrorType.PARAMETER_INVALID, e.getMessage());
        return handleExceptionInternal(e, error, httpHeaders, status, request);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<?> handlerServerError(final ServerErrorException e, final WebRequest request, final HttpHeaders httpHeaders) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var error = createStandarErrorBuilder(HttpStatus.BAD_REQUEST, StandardErrorType.SERVER_ERROR, e.getMessage());
        return handleExceptionInternal(e, error, httpHeaders, status, request);
    }

    private StandardError createStandarErrorBuilder(HttpStatus status, StandardErrorType parameterInvalid, String message) {
        return StandardError.builder()
                .detail(message)
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .title(status.getReasonPhrase())
                .build();
    }
}
