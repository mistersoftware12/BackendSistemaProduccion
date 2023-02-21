package com.Biblioteca.DTO.empresa.sucursales;

import lombok.Data;

import java.io.Serializable;

@Data
public class BodegaRequest implements Serializable {
    private  Long id;

    private Long idBodega;

    private String nombre;

    private String direccion;

    private String telefono;

    private String correo;

    private String responsable;

    private Boolean estado;

    private Long idSucursal;

    private String nombreSucursal;
}
