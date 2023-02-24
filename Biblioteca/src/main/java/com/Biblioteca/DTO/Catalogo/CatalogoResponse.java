package com.Biblioteca.DTO.Catalogo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogoResponse implements Serializable {

    private Long id;
    private String nombre;
    private  Boolean estado;

    public CatalogoResponse(Long id, String nombre,  Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public CatalogoResponse() {

    }
}
