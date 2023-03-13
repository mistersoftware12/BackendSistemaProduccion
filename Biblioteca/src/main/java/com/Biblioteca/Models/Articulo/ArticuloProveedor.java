package com.Biblioteca.Models.Articulo;

import com.Biblioteca.Models.Persona.Proveedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "articulo_proveedor")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArticuloProveedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "articulo_id",referencedColumnName = "id")
    private Articulo articulo;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "proveedor_id",referencedColumnName = "id")
    private Proveedor proveedor;


    private float precioCompra;
}
