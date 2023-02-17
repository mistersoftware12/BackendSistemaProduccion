package com.Biblioteca.Models.Empresa;

import com.Biblioteca.Models.Empresa.Sucursales.Almacen;
import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import com.Biblioteca.Models.Empresa.Sucursales.Bodega;
import com.Biblioteca.Models.Empresa.Sucursales.Taller;
import com.Biblioteca.Models.Persona.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sucursal")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal  implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private  String logo;

    @OneToOne(mappedBy = "sucursal")
    private Bodega bodega;
}


