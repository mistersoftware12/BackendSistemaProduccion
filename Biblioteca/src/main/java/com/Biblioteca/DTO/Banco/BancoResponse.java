package com.Biblioteca.DTO.Banco;

import lombok.Data;

import java.io.Serializable;

@Data
public class BancoResponse implements Serializable {

    private Long id;
    private String nombre;

    public BancoResponse(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public BancoResponse() {

    }
}
