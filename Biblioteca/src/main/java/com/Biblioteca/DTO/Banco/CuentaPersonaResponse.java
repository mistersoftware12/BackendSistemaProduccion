package com.Biblioteca.DTO.Banco;

import lombok.Data;

import java.io.Serializable;

@Data
public class CuentaPersonaResponse implements Serializable {

    private Long id;

    private String propietario;

    private String numeroCuenta;

    private boolean estado;

    private Long idBanco;

    private Long idTipoCuenta;

    public CuentaPersonaResponse(Long id, String propietario, String numeroCuenta, boolean estado, Long idBanco, Long idTipoCuenta) {
        this.id = id;
        this.propietario = propietario;
        this.numeroCuenta = numeroCuenta;
        this.estado = estado;
        this.idBanco = idBanco;
        this.idTipoCuenta = idTipoCuenta;
    }

    public CuentaPersonaResponse() {

    }
}
