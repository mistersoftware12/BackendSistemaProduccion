package com.Biblioteca.DTO.Banco;

import lombok.Data;

import java.io.Serializable;

@Data
public class CuentaPersonaRequest implements Serializable {

    private Long id;

    private String propietario;

    private String numeroCuenta;

    private boolean estado;

    private Long idBanco;

    private Long idTipoCuenta;



}
