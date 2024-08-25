package com.alejjandrodev.clientDataManager.unit.models;

import com.alejjandrodev.clientDataManager.models.Client;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {


    @Test
    void password_Must_Be_Encrypted() {
        String password = "password";
        Client newClient = new Client();
        newClient.setPassword(password);
        assertNotEquals(newClient.getPassword(), password);
    }

    @Test
    void password_Must_Be_hide_during_MAP() {
        String password = "password";
        Client newClient = new Client();
        newClient.setId((long) 12345);
        newClient.setNombre("Alejandro " + UUID.randomUUID());
        newClient.setEstado(true);
        newClient.setTelefono("04123515554");
        newClient.setGenero("M");
        newClient.setEdad(29);
        newClient.setPassword("123456");
        newClient.setIdentificacion("Alejandro " + UUID.randomUUID());
        newClient.setDireccion("Venezuela");

        Client mapClient = Client.mapClient(newClient);

        assertNull(mapClient.getPassword());
        assertNotNull(mapClient.getId());
        assertNotNull(mapClient.getNombre());
        assertNotNull(mapClient.getEstado());
        assertNotNull(mapClient.getTelefono());
        assertNotNull(mapClient.getGenero());
        assertNotNull(mapClient.getEdad());
        assertNotNull(mapClient.getIdentificacion());
    }





}