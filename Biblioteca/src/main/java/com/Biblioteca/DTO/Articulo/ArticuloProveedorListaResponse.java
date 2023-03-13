package com.Biblioteca.DTO.Articulo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticuloProveedorListaResponse implements Serializable {

    private Long id;
    private Long idArticulo;
    private Long idProveedor;
    private float precioCompra;
    private String nombreProveedor;
    private String foto;
    private String codigoBarra;
    private String nombreArticulo;
    private String descripcion;

    public ArticuloProveedorListaResponse(Long id, Long idArticulo, Long idProveedor, float precioCompra, String nombreProveedor, String foto, String codigoBarra, String nombreArticulo, String descripcion) {
        this.id = id;
        this.idArticulo = idArticulo;
        this.idProveedor = idProveedor;
        this.precioCompra = precioCompra;
        this.nombreProveedor = nombreProveedor;
        this.foto = foto;
        this.codigoBarra = codigoBarra;
        this.nombreArticulo = nombreArticulo;
        this.descripcion = descripcion;
    }

    public ArticuloProveedorListaResponse() {
    }

}
