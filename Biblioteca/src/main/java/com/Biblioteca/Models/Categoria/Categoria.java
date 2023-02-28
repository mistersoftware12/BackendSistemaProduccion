package com.Biblioteca.Models.Categoria;

import com.Biblioteca.Models.Articulo.Articulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "categoria")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "incial_codigo")
    private  String inicialCodigo;

    private  Boolean estado;

    /*
    @OneToOne(mappedBy = "categoria")
    private Articulo articulo;*/


    @OneToMany (targetEntity = Articulo.class, mappedBy = "categoria")
    private List<Articulo> articulo;

}


