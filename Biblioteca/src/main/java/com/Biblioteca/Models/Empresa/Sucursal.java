package com.Biblioteca.Models.Empresa;

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

    @Column(columnDefinition="text", length=10485760)
    private  String logo;


}


