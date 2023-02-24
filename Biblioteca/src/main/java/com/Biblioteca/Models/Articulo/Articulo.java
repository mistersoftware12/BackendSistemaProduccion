package com.Biblioteca.Models.Articulo;

import com.Biblioteca.Models.Catalogo.Catalogo;
import com.Biblioteca.Models.Categoria.Categoria;
import com.Biblioteca.Models.Persona.Persona;
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

    private String codigoBarra;

    private Boolean estadoArticulo;

    private Boolean estadoWeb;

    private String codigoCompra;

    private String marca;

    private String vidaUtil;

    //medida
    private float alto;

    private float ancho;

    private float profundidad;

    private float peso;


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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="catalogo_id", referencedColumnName = "id")
    private Catalogo catalogo;


}
