package com.Biblioteca.Repository.Banco;

import com.Biblioteca.Models.Banco.CuentaPersona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaPersonaRepository extends JpaRepository<CuentaPersona, Long> {

}
