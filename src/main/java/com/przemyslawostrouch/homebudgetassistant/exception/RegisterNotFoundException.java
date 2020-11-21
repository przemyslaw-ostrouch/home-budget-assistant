package com.przemyslawostrouch.homebudgetassistant.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegisterNotFoundException extends RuntimeException {

    public RegisterNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
