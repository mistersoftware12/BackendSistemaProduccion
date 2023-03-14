package com.Biblioteca.Models.Banco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "cuentaPersona")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaPersona implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propietario;

    private String numeroCuenta;

    private boolean estado;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "banco_id",referencedColumnName = "id")
    private Banco banco;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "tipocuenta_id",referencedColumnName = "id")
    private TipoCuenta tipoCuenta;




}


