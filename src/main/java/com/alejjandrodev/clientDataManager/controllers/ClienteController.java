package com.alejjandrodev.clientDataManager.controllers;

import com.alejjandrodev.clientDataManager.common.ValidationGroups;
import com.alejjandrodev.clientDataManager.models.Client;
import com.alejjandrodev.clientDataManager.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Page<Client> getClientes(Pageable pageable) {
        return this.clienteService.findAll(pageable).map(client -> Client.mapClient(client));
    }

    @GetMapping("/name/{name}")
    public Client search(@PathVariable  String name){
        return  Client.mapClient(clienteService.findByNombre(name));
    }

    @GetMapping("/{id}")
    public Client getOne(@PathVariable  Long id){
        return Client.mapClient(this.clienteService.findById(id));
    }

    @PostMapping
    public Client create(@Validated(ValidationGroups.Create.class) @RequestBody Client client){
        return Client.mapClient(this.clienteService.create(client));
    }

    @PutMapping
    public Client set(@Valid @RequestBody Client client){
        return Client.mapClient(this.clienteService.set(client));
    }

    @PatchMapping("/{id}")
    public Client update(@PathVariable  Long id, @Valid @RequestBody Client client){
        return Client.mapClient(this.clienteService.update(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable  Long id){
         this.clienteService.delete(id);
         return ResponseEntity.noContent().build();
    }


}
