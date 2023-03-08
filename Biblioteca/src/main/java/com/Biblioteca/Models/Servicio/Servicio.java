package com.Biblioteca.Models.Servicio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "servicio")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private boolean estado;

    private  String codigo_barra;

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

}
