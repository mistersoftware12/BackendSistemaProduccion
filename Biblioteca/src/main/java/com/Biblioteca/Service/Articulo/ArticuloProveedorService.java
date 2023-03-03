package com.Biblioteca.Service.Articulo;


import com.Biblioteca.DTO.Articulo.ArticuloProveedorRequest;
import com.Biblioteca.DTO.Articulo.ArticuloProveedorResponse;
import com.Biblioteca.DTO.empresa.sucursales.SucursalRequest;
import com.Biblioteca.DTO.empresa.sucursales.TallerResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Articulo.Articulo;
import com.Biblioteca.Models.Articulo.ArticuloProveedor;
import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Models.Empresa.Sucursales.Taller;
import com.Biblioteca.Models.Persona.Proveedor;
import com.Biblioteca.Repository.Articulo.ArticuloProveedorRepository;
import com.Biblioteca.Repository.Articulo.ArticuloRepository;
import com.Biblioteca.Repository.Persona.ProveedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticuloProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    ArticuloProveedorRepository articuloProveedorRepository;



    public List<ArticuloProveedorResponse> listAllArticuloProveedor(Long idArticulo){

        Optional<ArticuloProveedor> articuloProveedors = articuloProveedorRepository.findByArticuloId(idArticulo);

        return articuloProveedors.stream().map( artTalRequest->{

            ArticuloProveedorResponse pcr = new ArticuloProveedorResponse();
            pcr.setId(artTalRequest.getId());
            pcr.setIdArticulo(artTalRequest.getArticulo().getId());
            pcr.setIdProveedor(artTalRequest.getProveedor().getId());
            return pcr;
        }).collect(Collectors.toList());
    }

    public boolean regitrarArticuloProveedor (ArticuloProveedorRequest articuloProveedorRequest ){

        Optional<Articulo> optionalArticulo = articuloRepository.findById(articuloProveedorRequest.getIdArticulo());

        if(optionalArticulo.isPresent()){

            Optional<Proveedor> optionalProveedor = proveedorRepository.findById(articuloProveedorRequest.getIdProveedor());

            if(optionalProveedor.isPresent()){


                if(count1(articuloProveedorRequest.getIdArticulo(),articuloProveedorRequest.getIdProveedor()) == BigInteger.valueOf(0)){

                    ArticuloProveedor neArticuloProveedor = new ArticuloProveedor();
                    neArticuloProveedor.setArticulo(optionalArticulo.get());
                    neArticuloProveedor.setProveedor(optionalProveedor.get());


                    try {
                        articuloProveedorRepository.save(neArticuloProveedor);
                        return true;

                    }catch (Exception e){
                        throw new BadRequestException("No se registró la sucursal" +e);

                    }

                }else {
                    throw new BadRequestException("Ya existe un artículo : "+articuloProveedorRequest.getIdArticulo() +" con un proveedor "+articuloProveedorRequest.getIdProveedor());
                }


            }else{
                throw new BadRequestException("No existe un proveedor con id: "+articuloProveedorRequest.getIdProveedor());
            }
        }else {
            throw new BadRequestException("No existe un articulo con id: "+articuloProveedorRequest.getIdArticulo());
        }


    }


    @PersistenceContext
    private EntityManager entityManager;

    public BigInteger count1(Long idArticulo , Long idProveedor) {
        Query nativeQuery = entityManager.createNativeQuery("SELECT COUNT(id) FROM articulo_proveedor WHERE articulo_id =? AND proveedor_id =?");
        nativeQuery.setParameter(1, idArticulo);
        nativeQuery.setParameter(2, idProveedor);
        return (BigInteger) nativeQuery.getSingleResult();
    }

}
