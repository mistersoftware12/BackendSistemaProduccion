package com.Biblioteca.DTO.Articulo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticuloRequest implements Serializable {

    private Long id;
    private String nombre;
    private String descripcion;
    private Integer stockMinimo;
    private String color;
    private  String foto;
    private String codigoBarra;
    private Boolean estadoArticulo;
    private Boolean estadoWeb;
    private String codigoCompra;
    private String marca;
    private String vidaUtil;

    //medida
        private String alto;
    private String ancho;
    private String profundidad;
    private String peso;


    //precioproducccion

    private float precioCosto;
    private float iva;
    private float precioIva;
    private float precioStandar;
    private float margenProduccion;
    private float precioProduccion;
    private float margenVenta;
    private float precioVenta;
    private float precioFinal;


    //herencia

    private Long idCategoria;
    private Long idCatalogo;

    //Extras
    private String nombreCategoria;
    private  String nombreCatalogo;
    private String nombreEstadoArticulo;
    private String nombreEstadoWeb;


}
