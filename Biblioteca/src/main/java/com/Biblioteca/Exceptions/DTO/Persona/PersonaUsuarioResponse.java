package com.Biblioteca.Exceptions.DTO.Persona;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaUsuarioResponse implements Serializable {

    private Long id;

    private Long idUsuario;
    private String cedula;

    private String apellidos;

    private String nombres;

    private String email;

    private String telefono;

    private String clave;

    private String direccion;

    private Date fechaNacimiento;

    private Long idRol;


    private Long idSucursal;

    private String token;

    public PersonaUsuarioResponse(Long id, String cedula, String apellidos, String nombres,  String email, String telefono, String clave, Long idRol,String token) {
        this.id = id;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.telefono = telefono;
        this.clave = clave;
        this.idRol = idRol;
        this.token = token;
    }


    public PersonaUsuarioResponse(Long id, String cedula, String apellidos, String nombres, String email, String telefono, String clave, String direccion, Date fechaNacimiento, Long idRol, Long idSucursal, String token) {
        this.id = id;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.telefono = telefono;
        this.clave = clave;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.idRol = idRol;
        this.idSucursal = idSucursal;
        this.token = token;
    }

    public PersonaUsuarioResponse() {

    }
}
