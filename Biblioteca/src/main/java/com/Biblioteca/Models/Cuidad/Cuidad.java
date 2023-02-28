package com.Biblioteca.Models.Cuidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "cuidad")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuidad  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

}
