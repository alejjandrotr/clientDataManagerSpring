package com.alejjandrodev.clientDataManager.services;

import com.alejjandrodev.clientDataManager.AcountDataManager.AccountDataManagerClient;
import com.alejjandrodev.clientDataManager.AcountDataManager.AccountDataManagerClientImpl;
import com.alejjandrodev.clientDataManager.errors.DuplicateKeyException;
import com.alejjandrodev.clientDataManager.errors.NotFoudClientError;
import com.alejjandrodev.clientDataManager.models.Client;
import com.alejjandrodev.clientDataManager.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private AccountDataManagerClient accountDataManagerClient;

    @Autowired
    void setAccountDataManagerClient(AccountDataManagerClientImpl accountDataManagerClient) {
        this.accountDataManagerClient = accountDataManagerClient;
    }

    @Autowired
    private ClientRepository clientRepository;

    public Page<Client> findAll(Pageable page) {
        this.logger.info("Begin fetching all clients " + page.getPageNumber() + " and " + page.getPageSize());
        Page<Client> clients= this.clientRepository.findAll(page);
        this.logger.info("End fetching all clients " + page.getPageNumber() + " and " + page.getPageSize());
        return clients;
    }

    public Client findById(long id) {
        Client clientFound =  this.clientRepository.findById(id);
        if (clientFound == null) {
            this.logger.error("Not found client with id " + id);
            throw new NotFoudClientError();
        }
        this.logger.info("Found client with id " + id);
        return clientFound;
    }

    public Client create(Client client) {
        this.logger.info("Creating new client with name: "+ client.getNombre());
        if (client.getIdentificacion() == null) {
            client.setIdentificacion(client.getNombre());
        }
        try {
            Client newClient =  clientRepository.save(client);
            this.logger.info("New client saved with id " + newClient.getIdentificacion());
            return newClient;
        } catch (DataIntegrityViolationException ex) {
            String message = ex.getMessage();
            if (message.toLowerCase().contains("duplicate")) {
                throw new DuplicateKeyException();
            } else {
                throw ex;
            }
        }
    }

    public Client set(Client client) {
        this.logger.info("Change client data with name: "+ client.getNombre());
        Client clientFound = this.findById(client.getId());
        if (!clientFound.getNombre().equalsIgnoreCase(client.getNombre()) ){
            this.accountDataManagerClient.changeAccountNameFromClient(client.getId(), client.getNombre());
        }
        Client upadtedClient =  this.clientRepository.save(client);
        this.logger.info("Changed client data with name: "+ client.getNombre());
        return upadtedClient;
    }

    public Client update(Long id, @Valid Client clientDto) {
        Client clientFound = this.findById(id);
        this.logger.info("Change client data with name: "+ clientDto.getNombre());
        if (clientDto.getPassword() != null ){
            clientFound.setPassword(clientDto.getPassword());
        }

        if (clientDto.getIdentificacion() != null ){
            clientFound.setIdentificacion(clientDto.getIdentificacion());
        }

        if (clientDto.getEstado() != null ){
            clientFound.setEstado(clientDto.getEstado());
        }

        if (clientDto.getDireccion() != null ){
            clientFound.setDireccion(clientDto.getDireccion());
        }

        if (clientDto.getGenero() != null ){
            clientFound.setGenero(clientDto.getGenero());
        }

        if (clientDto.getTelefono() != null ){
            clientFound.setTelefono(clientDto.getTelefono());
        }

        if (clientDto.getNombre() != null ){
            this.accountDataManagerClient.changeAccountNameFromClient(id, clientDto.getNombre());
            clientFound.setNombre(clientDto.getNombre());
        }

        if (clientDto.getEdad() != null ){
            clientFound.setEdad(clientDto.getEdad());
        }

        this.logger.info("Changed client data with name: "+ clientDto.getNombre());
        return this.clientRepository.save(clientFound);
    }

    public void delete(Long id) {
        this.logger.info("Deleting client with id " + id);
        this.findById(id);
        this.accountDataManagerClient.deleteAccountFromClient(id);
        this.clientRepository.deleteById(id);
        this.logger.info("Deleted client with id " + id);
    }

    public Client findByNombre(String name) {
        Client clientFound =  this.clientRepository.findFirstByNombre(name);
        if (clientFound == null ) {
            this.logger.info("Not found client with name " + name);
            throw new NotFoudClientError();
        }
        return clientFound;
    }
}
