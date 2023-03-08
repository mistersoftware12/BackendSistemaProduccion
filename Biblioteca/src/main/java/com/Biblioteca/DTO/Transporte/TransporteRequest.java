package com.Biblioteca.DTO.Transporte;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransporteRequest implements Serializable {

    private Long id;

    private String nombre;

    private boolean estado;

    private String descripcion;

    //extras

    private String nombreEstado;

}
