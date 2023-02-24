package com.Biblioteca.DTO.Categoria;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriaResponse implements Serializable {

    private Long id;
    private String nombre;
    private String inicialCodigo;

    private  Boolean estado;

    private String nombreEstado;



    public CategoriaResponse(Long id, String nombre, String inicialCodigo, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.inicialCodigo = inicialCodigo;
        this.estado = estado;
    }

    public CategoriaResponse() {

    }
}
