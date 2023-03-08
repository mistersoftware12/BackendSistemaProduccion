package com.Biblioteca.DTO.Transporte;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransporteResponse implements Serializable {

    private Long id;

    private String nombre;

    private boolean estado;

    private String descripcion;

    //extras

    private String nombreEstado;

    public TransporteResponse() {
    }

    public TransporteResponse(Long id, String nombre, boolean estado, String descripcion, String nombreEstado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.nombreEstado = nombreEstado;
    }
}
