package com.alejjandrodev.clientDataManager.AcountDataManager.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RenameAccountClientError extends ResponseStatusException {
    public RenameAccountClientError(Long id) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Account Reneme Client Error: no se cambiar los nombres de  las cuentas de " + id);
    }
}
