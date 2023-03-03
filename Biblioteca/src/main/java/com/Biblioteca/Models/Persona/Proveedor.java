package com.Biblioteca.Models.Persona;

import com.Biblioteca.Models.Catalogo.Catalogo;
import com.Biblioteca.Models.Ubicacion.Cuidad;
import com.Biblioteca.Models.Ubicacion.Pais;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "proveedor")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombrebanco;

    private String numeroCuenta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="persona_id", referencedColumnName = "id")
    private Persona persona;


    private String nombreCuidad;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="pais_id", referencedColumnName = "id")
    private Pais pais;


}
