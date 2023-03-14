package com.Biblioteca.DTO.Banco;

import lombok.Data;

import java.io.Serializable;

@Data
public class BancoRequest implements Serializable {

    private Long id;
    private String nombre;

}
