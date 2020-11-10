package com.web.assgiment.dormitory.exception;

import com.web.assgiment.dormitory.common.respond.ResponseData;
import com.web.assgiment.dormitory.utils.MessageBundle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class HandlingExceptionDocument extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class,
            IllegalStateException.class})
    public ResponseEntity<?> handleInternalServerException(InternalServerException ex) {
        return ResponseEntity.ok(ResponseData.ofFail(MessageBundle.getMessage("dormitory.message.system", ex.getMessage())));
    }

    @ExceptionHandler(UserValidateException.class)
    public ResponseEntity<?> handleUserException(UserValidateException ex) {
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
    }


}
