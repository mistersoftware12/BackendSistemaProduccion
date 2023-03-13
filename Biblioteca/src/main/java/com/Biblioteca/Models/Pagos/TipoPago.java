package com.Biblioteca.Models.Pagos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "tipoPago")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

}
