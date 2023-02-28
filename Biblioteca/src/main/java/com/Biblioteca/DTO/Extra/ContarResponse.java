package com.Biblioteca.DTO.Extra;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContarResponse implements Serializable {
    private long numeroRegistro;

    public ContarResponse(long numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public ContarResponse() {

    }
}
