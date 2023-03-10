package com.Biblioteca.Service.Articulo;


import com.Biblioteca.DTO.Articulo.ArticuloProveedorListaResponse;
import com.Biblioteca.DTO.Articulo.ArticuloProveedorRequest;
import com.Biblioteca.DTO.Articulo.ArticuloProveedorResponse;
import com.Biblioteca.DTO.empresa.sucursales.SucursalRequest;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Articulo.Articulo;
import com.Biblioteca.Models.Articulo.ArticuloProveedor;
import com.Biblioteca.Models.Empresa.Sucursal;
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
import javax.transaction.Transactional;
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



    public List<ArticuloProveedorResponse> listAllArticuloProveedorByIdArticulo(Long idArticulo){

        List<ArticuloProveedor> articuloProveedors = articuloProveedorRepository.findByArticuloId(idArticulo);


        return articuloProveedors.stream().map( artTalRequest->{

            ArticuloProveedorResponse pcr = new ArticuloProveedorResponse();
            pcr.setId(artTalRequest.getId());
            pcr.setIdArticulo(artTalRequest.getArticulo().getId());
            pcr.setIdProveedor(artTalRequest.getProveedor().getId());
            pcr.setNombreProveedor(artTalRequest.getProveedor().getPersona().getNombres()+" "+artTalRequest.getProveedor().getPersona().getApellidos());
            pcr.setPrecioCompra(artTalRequest.getPrecioCompra());
            return pcr;
        }).collect(Collectors.toList());
    }


    public List<ArticuloProveedorListaResponse> listAllArticuloProveedorByIdProveedor(Long idProveedor){

        List<ArticuloProveedor> articuloProveedors = articuloProveedorRepository.findByProveedorId(idProveedor);

        return articuloProveedors.stream().map( artTalRequest->{

            ArticuloProveedorListaResponse pcr = new ArticuloProveedorListaResponse();
            pcr.setId(artTalRequest.getId());
            pcr.setIdArticulo(artTalRequest.getArticulo().getId());
            pcr.setIdProveedor(artTalRequest.getProveedor().getId());
            pcr.setNombreProveedor(artTalRequest.getProveedor().getPersona().getNombres()+" "+artTalRequest.getProveedor().getPersona().getApellidos());
            pcr.setPrecioCompra(artTalRequest.getPrecioCompra());
            pcr.setCodigoBarra(artTalRequest.getArticulo().getCodigoBarra());
            pcr.setFoto(artTalRequest.getArticulo().getFoto());
            pcr.setNombreArticulo(artTalRequest.getArticulo().getNombre());
            pcr.setDescripcion(artTalRequest.getArticulo().getDescripcion());
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
                    neArticuloProveedor.setPrecioCompra(articuloProveedorRequest.getPrecioCompra());

                    try {
                        articuloProveedorRepository.save(neArticuloProveedor);
                        return true;

                    }catch (Exception e){
                        throw new BadRequestException("No se registr?? el articulo proveedor" +e);

                    }

                }else {
                    throw new BadRequestException("Ya existe un art??culo : "+articuloProveedorRequest.getIdArticulo() +" con un proveedor "+articuloProveedorRequest.getIdProveedor());
                }


            }else{
                throw new BadRequestException("No existe un proveedor con id: "+articuloProveedorRequest.getIdProveedor());
            }
        }else {
            throw new BadRequestException("No existe un articulo con id: "+articuloProveedorRequest.getIdArticulo());
        }


    }

    @Transactional
    public boolean actualizarPrecioArticuloProveedor(ArticuloProveedorRequest articuloProveedorRequest){

        Optional<ArticuloProveedor> articuloProveedor = articuloProveedorRepository.findById(articuloProveedorRequest.getId());

        if(articuloProveedor.isPresent()){
            articuloProveedor.get().setPrecioCompra((articuloProveedorRequest.getPrecioCompra()));
            try{
                articuloProveedorRepository.save(articuloProveedor.get());
                return true;
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizo" + ex);
            }
        } else {
            throw new BadRequestException("No existe un articulo proveedor con id "+articuloProveedorRequest.getId() );
        }

    }

    public void deleteById(Long id) {

        Optional<ArticuloProveedor> optional = articuloProveedorRepository.findById(id);
        if (optional.isPresent()) {
            articuloProveedorRepository.deleteById(id);

        } else {
            throw new BadRequestException("El Articulo Proveedor con el id " + id + ", no existe");
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
