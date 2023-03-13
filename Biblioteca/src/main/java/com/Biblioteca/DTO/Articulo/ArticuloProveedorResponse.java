package com.Biblioteca.DTO.Articulo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticuloProveedorResponse implements Serializable {

    private Long id;

    private Long idArticulo;
    private Long idProveedor;

    private String nombreProveedor;

    private float precioCompra;

    public ArticuloProveedorResponse(Long id, Long idArticulo, Long idProveedor, String nombreProveedor, float precioCompra) {
        this.id = id;
        this.idArticulo = idArticulo;
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.precioCompra = precioCompra;
    }

    public ArticuloProveedorResponse() {
    }

}
