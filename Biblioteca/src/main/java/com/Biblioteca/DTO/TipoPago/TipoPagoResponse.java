package com.Biblioteca.DTO.TipoPago;

import lombok.Data;

import java.io.Serializable;

@Data
public class TipoPagoResponse implements Serializable {

    private Long id;
    private String nombre;

    public TipoPagoResponse(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoPagoResponse() {

    }
}
