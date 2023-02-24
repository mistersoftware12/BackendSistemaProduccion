package com.Biblioteca.DTO.Catalogo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogoRequest implements Serializable {

    private Long id;
    private String nombre;
    private  Boolean estado;

}
