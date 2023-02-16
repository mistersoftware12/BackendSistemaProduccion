package com.Biblioteca.DTO.empresa;

import java.io.Serializable;

public class SucursalResponse  implements Serializable {

    private String nombre;

    private Long idAlmacen;

    private Long idTaller;

    private Long idBodega;


    public SucursalResponse(String nombre, Long idAlmacen, Long idTaller, Long idBodega) {
        this.nombre = nombre;
        this.idAlmacen = idAlmacen;
        this.idTaller = idTaller;
        this.idBodega = idBodega;
    }
}
