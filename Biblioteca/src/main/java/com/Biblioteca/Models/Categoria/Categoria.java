package com.Biblioteca.Models.Categoria;

import com.Biblioteca.Models.Articulo.Articulo;
import com.Biblioteca.Models.Persona.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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

    @OneToOne(mappedBy = "categoria")
    private Articulo articulo;

}


