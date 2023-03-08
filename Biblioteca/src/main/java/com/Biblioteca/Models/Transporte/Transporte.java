package com.Biblioteca.Models.Transporte;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "trasporte")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transporte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private boolean estado;

    private String descripcion;

}
