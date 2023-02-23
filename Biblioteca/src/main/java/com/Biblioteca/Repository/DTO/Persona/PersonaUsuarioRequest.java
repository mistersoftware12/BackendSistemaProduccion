package com.Biblioteca.Repository.DTO.Persona;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaUsuarioRequest implements Serializable {

    private Long id;

    private String cedula;

    private String apellidos;

    private String nombres;

    private String email;

    private String telefono;

    private String direccion;

    private Date fechaNacimiento;

    private String clave;

    private Long idRol;

    private String nombreRol;

    private Long idSucursal;

    private String nombreSucursal;


}
