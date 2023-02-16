package com.Biblioteca.Models.Empresa;

import com.Biblioteca.Models.Empresa.Sucursales.Almacen;
import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import com.Biblioteca.Models.Empresa.Sucursales.Bodega;
import com.Biblioteca.Models.Empresa.Sucursales.Taller;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="almacen_id", referencedColumnName = "id")
    private Almacen almacen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bodega_id", referencedColumnName = "id")
    private Bodega bodega;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="taller_id", referencedColumnName = "id")
    private Taller taller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sucursal_id", referencedColumnName = "id")
    private Sucursal sucursal;
}
