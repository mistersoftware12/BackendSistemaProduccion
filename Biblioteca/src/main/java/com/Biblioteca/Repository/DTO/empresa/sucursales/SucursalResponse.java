package com.Biblioteca.Repository.DTO.empresa.sucursales;

import lombok.Data;

import java.io.Serializable;

@Data
public class SucursalResponse implements Serializable {

    private Long id;
    private String nombre;
    private String logo;

    public SucursalResponse(Long id, String nombre, String logo) {

        this.id = id;
        this.nombre = nombre;
        this.logo = logo;
    }

    public SucursalResponse() {

    }
}
