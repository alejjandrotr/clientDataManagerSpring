package com.alejjandrodev.clientDataManager.models;

import com.alejjandrodev.clientDataManager.common.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Table(name = "clientes")
public class Client extends Persona {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 5, max = 100)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean estado;

    public Client() {
    }

    public Client(Long id, String nombre, String password, Boolean estado) {
        this.id = id;
        this.password = password;
        this.estado = estado;
    }

    public Client(Long id, String nombre, String genero, String direccion, String telefono, String identificacion, Integer edad, String password, Boolean estado) {
        super(id, nombre, genero, direccion, telefono, identificacion, edad);
        this.password = password;
        this.estado = estado;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null)  return;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode(password);
        this.password = result;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    

    public static Client mapClient(Client client) {
       return client.clone();
    }

    @Override
    protected Client clone() {
        Client cloned = new Client();
        cloned.setId(id);
        cloned.setPassword(null);
        cloned.setEstado(estado);
        cloned.setEdad(this.getEdad());
        cloned.setGenero(this.getGenero());
        cloned.setDireccion(this.getDireccion());
        cloned.setTelefono(this.getTelefono());
        cloned.setIdentificacion(this.getIdentificacion());
        cloned.setNombre(this.getNombre());
        return cloned;
    }
}
