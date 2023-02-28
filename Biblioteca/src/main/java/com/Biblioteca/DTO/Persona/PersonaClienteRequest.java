package com.Biblioteca.DTO.Persona;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaClienteRequest implements Serializable {

    private Long id;

    private String cedula;

    private String apellidos;

    private String nombres;

    private String email;

    private String telefono;

    private String direccion;

    private Date fechaNacimiento;

    private  Long idCuidad;

    private  String nombreCuidad;



}
