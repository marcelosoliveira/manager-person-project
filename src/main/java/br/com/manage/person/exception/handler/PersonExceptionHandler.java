package br.com.manage.person.exception.handler;

import br.com.manage.person.exception.PersonNotFoundException;
import br.com.manage.person.exception.UserNameExistsException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<PersonProblem.Field> fields = new ArrayList<>();

        for (ObjectError error: ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new PersonProblem.Field(name, message));
        }

        PersonProblem personProblem = new PersonProblem();
        personProblem.setStatus(status.value());
        personProblem.setDateTime(LocalDateTime.now());
        personProblem.setTitle("One or more fields are invalid. Fill it in correctly and try again");
        personProblem.setFields(fields);

        return handleExceptionInternal(ex, personProblem, headers, status, request);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Object> handlerPersonNotFoundException(PersonNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        PersonProblem personProblem = new PersonProblem();
        personProblem.setStatus(status.value());
        personProblem.setDateTime(LocalDateTime.now());
        personProblem.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, personProblem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserNameExistsException.class)
    public ResponseEntity<Object> handlerUserNameExistsException(UserNameExistsException ex,
                                                                 WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        PersonProblem personProblem = new PersonProblem();
        personProblem.setStatus(status.value());
        personProblem.setDateTime(LocalDateTime.now());
        personProblem.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, personProblem, new HttpHeaders(), status, request);
    }
}
