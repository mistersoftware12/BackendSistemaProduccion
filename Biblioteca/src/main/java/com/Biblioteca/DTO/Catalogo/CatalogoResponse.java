package com.Biblioteca.DTO.Catalogo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogoResponse implements Serializable {

    private Long id;
    private String nombre;
    private String inicialCodigo;

    public CatalogoResponse(Long id, String nombre, String inicialCodigo) {
        this.id = id;
        this.nombre = nombre;
        this.inicialCodigo = inicialCodigo;
    }

    public CatalogoResponse() {

    }
}
