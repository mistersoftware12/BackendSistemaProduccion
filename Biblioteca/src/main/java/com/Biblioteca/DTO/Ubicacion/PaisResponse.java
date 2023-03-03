package com.Biblioteca.DTO.Ubicacion;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaisResponse implements Serializable {

    private Long id;
    private String nombre;


    public PaisResponse(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public PaisResponse() {

    }
}
