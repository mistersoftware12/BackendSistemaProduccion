package com.Biblioteca.Repository.Catalogo;

import com.Biblioteca.Models.Catalogo.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {


    //Optional<Sucursal> findBySucursalLikeIgnoreCase(String sucursal);


}
