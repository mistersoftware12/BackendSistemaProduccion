package com.Biblioteca.DTO.empresa;

import java.io.Serializable;

public class SucursalRequest implements Serializable {
    Long id;

    private String nombre;

    private Long idAlmacen;

    private Long idBodega;

    private Long idTaller;
}
