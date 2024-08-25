package com.alejjandrodev.clientDataManager.errors;

public class DuplicateKeyException extends RuntimeException {
    public DuplicateKeyException() {
        super("The value for the field identification or name is alredy in use for another client, please use other to identify the client");
    }
}
