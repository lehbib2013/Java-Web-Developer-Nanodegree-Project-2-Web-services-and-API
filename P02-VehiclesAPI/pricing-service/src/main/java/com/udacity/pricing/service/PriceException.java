package com.udacity.pricing.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cannot find price for Vehicle")
public class PriceException extends Exception {

    public PriceException(String message) {
        super(message);
    }
}
