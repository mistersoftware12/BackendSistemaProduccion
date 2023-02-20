package com.Biblioteca.Models.Persona;



import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Models.Roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clave;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="persona_id", referencedColumnName = "id")
    private Persona persona;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="rol_id", referencedColumnName = "id")
    private Roles roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sucursal_id", referencedColumnName = "id")
    private Sucursal sucursal;

}
