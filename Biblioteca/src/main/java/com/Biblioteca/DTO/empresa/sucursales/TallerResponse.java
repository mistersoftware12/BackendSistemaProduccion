package com.Biblioteca.DTO.empresa.sucursales;

import lombok.Data;

import java.io.Serializable;

@Data
public class TallerResponse implements Serializable {

    private  Long id;

    private Long idTaller;

    private String nombre;

    private String direccion;

    private String telefono;

    private String correo;

    private String responsable;

    private Boolean estado;

    private Long idSucursal;

    private String nombreSucursal;

    public TallerResponse(Long id, Long idTaller, String nombre, String direccion, String telefono, String correo, String responsable, Boolean estado, Long idSucursal) {
        this.id = id;
        this.idTaller = idTaller;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.responsable = responsable;
        this.estado = estado;
        this.idSucursal = idSucursal;
    }

    public TallerResponse() {
    }
}
