package com.alejjandrodev.clientDataManager.AcountDataManager;

public interface AccountDataManagerClient {
    void deleteAccountFromClient(Long id);
    void changeAccountNameFromClient(Long clientId, String newName);
}
