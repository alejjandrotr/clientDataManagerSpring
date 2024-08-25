package com.alejjandrodev.clientDataManager.AcountDataManager.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeleteAccountError  extends ResponseStatusException {
    public DeleteAccountError(Long id) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Account Delete Error: no se pudieron eliminar las cuentas de " + id);
    }
}
