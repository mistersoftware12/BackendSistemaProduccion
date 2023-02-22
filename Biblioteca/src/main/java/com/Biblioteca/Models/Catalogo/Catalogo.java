package com.Biblioteca.Models.Catalogo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "incial_codigo")
    private  String inicialCodigo;


}


