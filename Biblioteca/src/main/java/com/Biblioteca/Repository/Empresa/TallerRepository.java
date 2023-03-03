package com.Biblioteca.Repository.Empresa;

import com.Biblioteca.Models.Empresa.Sucursales.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TallerRepository extends JpaRepository<Taller,Long> {


    @Query(value = " SELECT * FROM taller WHERE sucursal_id  = :idSucu", nativeQuery = true)
    List<Taller> findAllByIdSucursal(Long idSucu);
}
