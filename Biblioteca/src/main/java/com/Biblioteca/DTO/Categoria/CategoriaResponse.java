package com.Biblioteca.DTO.Categoria;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriaResponse implements Serializable {

    private Long id;
    private String nombre;
    private String inicialCodigo;

    public CategoriaResponse(Long id, String nombre, String inicialCodigo) {
        this.id = id;
        this.nombre = nombre;
        this.inicialCodigo = inicialCodigo;
    }

    public CategoriaResponse() {

    }
}
