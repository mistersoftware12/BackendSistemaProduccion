package com.Biblioteca.Models.Persona;

import com.Biblioteca.Models.Ubicacion.Cuidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "cliente")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="persona_id", referencedColumnName = "id")
    private Persona persona;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cuidad_id", referencedColumnName = "id")
    private Cuidad cuidad;



}
