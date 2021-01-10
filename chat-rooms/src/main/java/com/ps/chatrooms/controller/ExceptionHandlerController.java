package com.ps.chatrooms.controller;

import com.ps.chatrooms.model.exception.SystemServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionHandlerController {

    @ExceptionHandler(SystemServiceException.class)
    public ResponseEntity<SystemServiceException> exception(SystemServiceException e) {
        return new ResponseEntity<SystemServiceException>(e, HttpStatus.BAD_REQUEST);
    }

}
