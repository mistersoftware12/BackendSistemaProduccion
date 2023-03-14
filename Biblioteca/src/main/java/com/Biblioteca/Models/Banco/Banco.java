package com.Biblioteca.Models.Banco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "banco")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Banco implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;


}


