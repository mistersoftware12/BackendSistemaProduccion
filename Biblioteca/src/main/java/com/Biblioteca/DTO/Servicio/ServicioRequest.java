package com.Biblioteca.DTO.Servicio;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServicioRequest implements Serializable {

    private Long id;

    private String nombre;

    private boolean estado;

    private  String codigo_barra;

    //Precio
    private float precioCosto;

    private float iva;

    private float precioIva;

    private float precioStandar;

    private float margenProduccion;

    private float precioProduccion;

    private float margenVenta;

    private float precioVenta;

    private float precioFinal;


    //extras

    private String nombreEstado;

}
