package com.Biblioteca.Exceptions.DTO.Persona;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioRequest implements Serializable {

    private String cedula, clave;
}
