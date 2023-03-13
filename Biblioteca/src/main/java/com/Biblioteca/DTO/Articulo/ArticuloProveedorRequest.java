package com.Biblioteca.DTO.Articulo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticuloProveedorRequest implements Serializable {


    private Long id;
    private Long idArticulo;
    private Long idProveedor;
    private float precioCompra;

    private String nombreProveedor;

}
