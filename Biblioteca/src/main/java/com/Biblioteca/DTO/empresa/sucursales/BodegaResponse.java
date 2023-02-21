package com.Biblioteca.DTO.empresa.sucursales;

import lombok.Data;

import java.io.Serializable;

@Data
public class BodegaResponse implements Serializable {

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

    public BodegaResponse(Long id, Long idBodega, String nombre, String direccion, String telefono, String correo, String responsable, Boolean estado, Long idSucursal) {
        this.id = id;
        this.idBodega = idBodega;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.responsable = responsable;
        this.estado = estado;
        this.idSucursal = idSucursal;
    }

    public BodegaResponse() {
    }
}
