package com.Biblioteca.DTO.Articulo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticuloResponse implements Serializable {

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


    public ArticuloResponse(Long id, String nombre, String descripcion, Integer stockMinimo, String color, String foto, String codigoBarra, Boolean estadoArticulo, Boolean estadoWeb, String codigoCompra, String marca, String vidaUtil, String alto, String ancho, String profundidad, String peso, float precioCosto, float iva, float precioIva, float precioStandar, float margenProduccion, float precioProduccion, float margenVenta, float precioVenta, float precioFinal, Long idCategoria, Long idCatalogo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stockMinimo = stockMinimo;
        this.color = color;
        this.foto = foto;
        this.codigoBarra = codigoBarra;
        this.estadoArticulo = estadoArticulo;
        this.estadoWeb = estadoWeb;
        this.codigoCompra = codigoCompra;
        this.marca = marca;
        this.vidaUtil = vidaUtil;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.peso = peso;
        this.precioCosto = precioCosto;
        this.iva = iva;
        this.precioIva = precioIva;
        this.precioStandar = precioStandar;
        this.margenProduccion = margenProduccion;
        this.precioProduccion = precioProduccion;
        this.margenVenta = margenVenta;
        this.precioVenta = precioVenta;
        this.precioFinal = precioFinal;
        this.idCategoria = idCategoria;
        this.idCatalogo = idCatalogo;
    }

    public ArticuloResponse() {
    }
}
