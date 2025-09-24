package com.scenarioautomation.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LampNotFoundException extends RuntimeException {
    public LampNotFoundException() {
        super("Lamp not found");
    }
}
