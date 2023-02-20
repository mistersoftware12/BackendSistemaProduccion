package com.Biblioteca.Exceptions.DTO.empresa.sucursales;

import lombok.Data;

import java.io.Serializable;

@Data
public class SucursalRequest implements Serializable {

    private Long id;
    private String nombre;
    private  String logo;

}
