package com.alejjandrodev.clientDataManager.AcountDataManager;

import com.alejjandrodev.clientDataManager.AcountDataManager.errors.DeleteAccountError;
import com.alejjandrodev.clientDataManager.AcountDataManager.errors.RenameAccountClientError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AccountDataManagerClientImpl implements AccountDataManagerClient {


    Logger logger = LoggerFactory.getLogger(AccountDataManagerClientImpl.class);

    @Value("${account-data-manager.url}")
    private String clienteDataManagerUrl;

    private final WebClient webClient;

    @Autowired
    public AccountDataManagerClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void deleteAccountFromClient(Long clientId) {
        try {
             webClient.delete()
                    .uri(this.clienteDataManagerUrl+"/cuentas/deleteAll/" + clientId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
             this.logger.info("All clients account has been deleted, clientId"+clientId);
        } catch (Exception e) {
            throw new DeleteAccountError(clientId);
        }
    }

    @Override
    public void changeAccountNameFromClient(Long clientId, String newName) {
        try {
            webClient.patch()
                    .uri(this.clienteDataManagerUrl+"/cuentas/renameAll/" + clientId+"?name="+newName)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            this.logger.info("All clients account name has been changed, clientId"+clientId);
        } catch (Exception e) {
            throw new RenameAccountClientError(clientId);
        }
    }
}
