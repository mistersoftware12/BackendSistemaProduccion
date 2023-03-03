package com.Biblioteca.Models.Articulo;

import com.Biblioteca.Models.Catalogo.Catalogo;
import com.Biblioteca.Models.Categoria.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "articulo")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private Integer stockMinimo;

    private String color;

    @Column(columnDefinition="text", length=10485760)
    private  String foto;

    @Column(length = 9)
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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "categoria_id",referencedColumnName = "id")
    private Categoria categoria;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "catalogo_id",referencedColumnName = "id")
    private Catalogo catalogo;


}
