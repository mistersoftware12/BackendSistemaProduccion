package com.Biblioteca.DTO.Persona;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaProveedorRequest implements Serializable {


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

    private  String nombrePais;

    private  String nombreCuidad;



}
