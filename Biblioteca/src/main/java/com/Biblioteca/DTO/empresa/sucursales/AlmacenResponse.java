package com.Biblioteca.DTO.empresa.sucursales;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlmacenResponse implements Serializable {


    private  Long id;

    private Long idAlmacen;

    private String nombre;

    private String direccion;

    private String telefono;

    private String correo;

    private String responsable;

    private Boolean estado;

    private Long idSucursal;

    private String nombreSucursal;

    public AlmacenResponse(Long id, Long idAlmacen, String nombre, String direccion, String telefono, String correo, String responsable, Boolean estado, Long idSucursal) {
        this.id = id;
        this.idAlmacen = idAlmacen;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.responsable = responsable;
        this.estado = estado;
        this.idSucursal = idSucursal;
    }

    public AlmacenResponse() {
    }


}
