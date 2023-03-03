package com.Biblioteca.DTO.Ubicacion;

import lombok.Data;

import java.io.Serializable;

@Data
public class CuidadResponse implements Serializable {

    private Long id;
    private String nombre;


    public CuidadResponse(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public CuidadResponse() {

    }
}
