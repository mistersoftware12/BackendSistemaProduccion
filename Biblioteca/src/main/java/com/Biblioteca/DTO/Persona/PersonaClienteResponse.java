package com.Biblioteca.DTO.Persona;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaClienteResponse implements Serializable {

    private Long id;

    private String cedula;

    private String apellidos;

    private String nombres;

    private String email;

    private String telefono;

    private String ubicacion;

    private Date fechaNacimiento;


    public PersonaClienteResponse(Long id, String cedula, String apellidos, String nombres, String email, String telefono, String ubicacion, Date fechaNacimiento) {
        this.id = id;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public PersonaClienteResponse() {
    }
}
