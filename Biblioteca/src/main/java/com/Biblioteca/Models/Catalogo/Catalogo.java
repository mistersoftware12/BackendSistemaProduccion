package com.Biblioteca.Models.Catalogo;

import com.Biblioteca.Models.Articulo.Articulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "catalogo")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Catalogo implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private  Boolean estado;

    /*
    @OneToOne(mappedBy = "catalogo")
    private Articulo articulo;*/

    @OneToMany (targetEntity = Articulo.class, mappedBy = "catalogo")
    private List<Articulo> articulo;

}


