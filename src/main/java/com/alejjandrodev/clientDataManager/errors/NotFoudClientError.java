package com.alejjandrodev.clientDataManager.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoudClientError extends ResponseStatusException {
    public NotFoudClientError() {
        super(HttpStatus.NOT_FOUND, "The client is not found.");
    }
}
