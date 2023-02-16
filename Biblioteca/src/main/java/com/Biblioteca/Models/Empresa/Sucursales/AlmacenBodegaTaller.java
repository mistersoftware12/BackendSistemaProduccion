package com.Biblioteca.Models.Empresa.Sucursales;

import com.Biblioteca.Models.Persona.Cliente;
import com.Biblioteca.Models.Persona.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "almacenBodegaTaller")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlmacenBodegaTaller  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    private String telefono;

    private String correo;

    private String responsable;

    private String logo;

    private Boolean estado;

    @OneToOne(mappedBy = "almacenBodegaTaller")
    private Almacen almacen;

    @OneToOne(mappedBy = "almacenBodegaTaller")
    private Bodega bodega;

    @OneToOne (mappedBy = "almacenBodegaTaller")
    private  Taller taller;

}
