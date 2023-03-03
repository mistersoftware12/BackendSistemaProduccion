package com.Biblioteca.DTO.Persona;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaProveedorResponse implements Serializable {


    private Long id;

    private Long idProveedor;

    private String cedula;

    private String apellidos;

    private String nombres;

    private String email;

    private String telefono;

    private String direccion;

    private Date fechaNacimiento;

    private String nombreBanco;

    private String numeroCuenta;

    private  Long idPais;

    private String nombrePais;

    private String nombreCuidad;


    public PersonaProveedorResponse(Long id, Long idProveedor, String cedula, String apellidos, String nombres, String email, String telefono, String direccion, Date fechaNacimiento, String nombreBanco, String numeroCuenta, Long idPais) {
        this.id = id;
        this.idProveedor = idProveedor;
        this.cedula = cedula;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.idPais = idPais;
    }

    public PersonaProveedorResponse() {
    }


}
