package com.Biblioteca.DTO.Ubicacion;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaisRequest implements Serializable {

    private Long id;
    private String nombre;

}
