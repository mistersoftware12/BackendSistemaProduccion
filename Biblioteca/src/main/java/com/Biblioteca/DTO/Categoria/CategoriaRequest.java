package com.Biblioteca.DTO.Categoria;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoriaRequest implements Serializable {

    private Long id;
    private String nombre;
    private  String inicialCodigo;

    private  Boolean estado;

    private String nombreEstado;

}
