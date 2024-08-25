package com.alejjandrodev.clientDataManager.repositories;

import com.alejjandrodev.clientDataManager.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface ClientRepository extends Repository<Client, Long> {
    Client save(Client client);
    Page<Client> findAll(Pageable pageable);
    Client findById(Long id);
    void deleteById(Long id);
    Client findFirstByNombre(String name);
}
