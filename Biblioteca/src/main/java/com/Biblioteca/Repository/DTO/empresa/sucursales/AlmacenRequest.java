package com.Biblioteca.Repository.DTO.empresa.sucursales;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlmacenRequest implements Serializable {


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

    private String nombreEstado;

}
