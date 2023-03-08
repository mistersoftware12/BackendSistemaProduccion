package com.Biblioteca.DTO.Servicio;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServicioResponse implements Serializable {

    private Long id;

    private String nombre;

    private boolean estado;

    private  String codigo_barra;

    private float precioCosto;

    private float iva;

    private float precioIva;

    private float precioStandar;

    private float margenProduccion;

    private float precioProduccion;

    private float margenVenta;

    private float precioVenta;

    private float precioFinal;


    //Extras
    private String nombreEstado;

    public ServicioResponse() {
    }

    public ServicioResponse(Long id, String nombre, boolean estado, String codigo_barra, float precioCosto, float iva, float precioIva, float precioStandar, float margenProduccion, float precioProduccion, float margenVenta, float precioVenta, float precioFinal) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.codigo_barra = codigo_barra;
        this.precioCosto = precioCosto;
        this.iva = iva;
        this.precioIva = precioIva;
        this.precioStandar = precioStandar;
        this.margenProduccion = margenProduccion;
        this.precioProduccion = precioProduccion;
        this.margenVenta = margenVenta;
        this.precioVenta = precioVenta;
        this.precioFinal = precioFinal;
    }
}
