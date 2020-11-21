package com.przemyslawostrouch.homebudgetassistant.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncorrectTransferValueException extends RuntimeException {

    public IncorrectTransferValueException(String message) {
        super(message);
        log.error(message);
    }
}
