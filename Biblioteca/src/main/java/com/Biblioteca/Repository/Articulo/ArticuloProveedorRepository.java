package com.Biblioteca.Repository.Articulo;


import com.Biblioteca.Models.Articulo.Articulo;
import com.Biblioteca.Models.Articulo.ArticuloProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ArticuloProveedorRepository extends JpaRepository<ArticuloProveedor,Long> {

    @Query(value = "SELECT * FROM articulo_proveedor WHERE articulo_id = :idArticulo", nativeQuery = true)
    List<ArticuloProveedor> findByArticuloId(Long idArticulo);

    // List<DatosEstadicticasMesAnio> findAllByMesandAnio(Long mes, Long anio);



}
