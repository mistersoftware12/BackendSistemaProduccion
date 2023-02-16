package com.Biblioteca.Models.Empresa.Sucursales;

import com.Biblioteca.Models.Empresa.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "bodega")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bodega  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="almacen_bodega_taller_id", referencedColumnName = "id")
    private AlmacenBodegaTaller almacenBodegaTaller;

    @OneToOne(mappedBy = "bodega")
    private Sucursal sucursal;
}
