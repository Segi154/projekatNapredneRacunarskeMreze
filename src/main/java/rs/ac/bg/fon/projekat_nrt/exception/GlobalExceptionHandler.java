package rs.ac.bg.fon.projekat_nrt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ResponseEntity<ExceptionResponse> buildErrorResponse(
            HttpStatus httpStatus,
            List<String> messages
    ) {
        messages.forEach(log::error);

        ExceptionResponse errorResponse = ExceptionResponse.builder()
                .statusCode(httpStatus.value())
                .messages(messages)
                .build();

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(
            NotFoundException ex
    ) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, List.of(ex.getMessage()));
    }

    @ExceptionHandler(SameTrainingShouldBeRecorded.class)
    public ResponseEntity<ExceptionResponse> handleDifferentTrainingRecords(SameTrainingShouldBeRecorded ex){
        return buildErrorResponse(HttpStatus.BAD_REQUEST,List.of(ex.getMessage()));
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
    })
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(Exception ex) {

        List<String> errorMessages = new ArrayList<>();


        if (ex instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            errorMessages = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
        }


        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessages);
    }

}

