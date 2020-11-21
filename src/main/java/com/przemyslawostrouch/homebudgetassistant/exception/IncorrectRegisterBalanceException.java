package com.przemyslawostrouch.homebudgetassistant.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncorrectRegisterBalanceException extends RuntimeException {

    public IncorrectRegisterBalanceException(String message) {
        super(message);
        log.error(message);
    }
}
