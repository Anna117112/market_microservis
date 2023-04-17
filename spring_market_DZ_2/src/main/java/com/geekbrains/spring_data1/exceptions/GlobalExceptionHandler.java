package com.geekbrains.spring_data1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    //хотитм перехватить исключение
    @ExceptionHandler
    // перехватываем исключение ResourceNotFoundException e или его наследников
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e) {
        // сообщение об ошибке для нас в лог
        // если перхватили 404 ошибку возвращаем сообщение об ошибке вытаскиваем его из exception e.getMessage()
        log.error(e.getMessage(),e);

        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<FieldValidationError> catchValidationException(ValidationException e) {
        // если перхватили 404 ошибку возвращаем сообщение об ошибке вытаскиваем его из exception e.getMessage()
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(new FieldValidationError(e.getErrorFieldMessage()), HttpStatus.BAD_REQUEST);
    }
}
