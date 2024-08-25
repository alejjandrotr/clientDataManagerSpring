package com.alejjandrodev.clientDataManager.integration.controllers.ClienteControllerTest;

import com.alejjandrodev.clientDataManager.AcountDataManager.AccountDataManagerClientImpl;
import com.alejjandrodev.clientDataManager.models.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountDataManagerClientImpl accountDataManagerClientImpl;




    @Test
    public void testGetClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", greaterThan(3)));
    }

    @Test
    public void testGetOneCliente() throws Exception {
        mockMvc.perform(get("/clientes/360"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").exists())
                .andExpect(jsonPath("$.contraseña").doesNotExist());
    }

    @Test
    public void testGetOneCliente_NOTFOUND() throws Exception {
        mockMvc.perform(get("/clientes/36212"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetOneClienteByNombre() throws Exception {
        mockMvc.perform(get("/clientes/name/Alejandro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").exists())
                .andExpect(jsonPath("$.contraseña").doesNotExist());
    }

    @Test
    public void testGetOneClienteByNombre_NOTFOUND() throws Exception {
        mockMvc.perform(get("/clientes/nombre/Alejandroasd"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPutClientes() throws Exception {
        Client client = this.createFakeMockClientObject();
        client.setId((long) 360);
        doNothing().when(accountDataManagerClientImpl).changeAccountNameFromClient(client.getId(), client.getNombre());

        mockMvc.perform(put("/clientes", client).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(client.getNombre()))
                .andExpect(jsonPath("$.contraseña").doesNotExist());;
    }

    @Test
    public void testPutClientes_Not_FOund() throws Exception {
        Client client = this.createFakeMockClientObject();
        client.setId((long) 3600);
        doNothing().when(accountDataManagerClientImpl).changeAccountNameFromClient(client.getId(), client.getNombre());

        mockMvc.perform(put("/clientes", client).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(client)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPatchClientes() throws Exception {
        Client client = this.createFakeMockClientObject();
        client.setId((long) 360);
        doNothing().when(accountDataManagerClientImpl).changeAccountNameFromClient(client.getId(), client.getNombre());

        mockMvc.perform(patch("/clientes/"+client.getId(), client).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(client.getNombre()))
                .andExpect(jsonPath("$.contraseña").doesNotExist());;
    }

    @Test
    public void testPatchClientes_NotFound() throws Exception {
        Client client = this.createFakeMockClientObject();
        client.setId((long) 36120);
        doNothing().when(accountDataManagerClientImpl).changeAccountNameFromClient(client.getId(), client.getNombre());

        mockMvc.perform(patch("/clientes/"+client.getId(), client).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(client)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteClientes() throws Exception {
        doNothing().when(accountDataManagerClientImpl).deleteAccountFromClient((long) 360);

        mockMvc.perform(delete("/clientes/"+360).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteClientes_NotFound() throws Exception {
        doNothing().when(accountDataManagerClientImpl).deleteAccountFromClient((long) 360);

        mockMvc.perform(delete("/clientes/"+360323).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private Client createFakeMockClientObject() {
        Client client = new Client();
        client.setNombre("Alejandro " + UUID.randomUUID());
        client.setEstado(true);
        client.setTelefono("04123515554");
        client.setGenero("M");
        client.setEdad(29);
        client.setPassword("123456");
        client.setIdentificacion("Alejandro " + UUID.randomUUID());
        client.setDireccion("Venezuela");


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(client);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            System.out.println("ERROR");
        }

        return client;
    }



}
