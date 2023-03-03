package com.Biblioteca.Service.Empresa;

import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.DTO.empresa.sucursales.AlmacenResponse;
import com.Biblioteca.DTO.empresa.sucursales.BodegaRequest;
import com.Biblioteca.DTO.empresa.sucursales.BodegaResponse;
import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Repository.Empresa.SucursalRepository;
import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import com.Biblioteca.Models.Empresa.Sucursales.Bodega;
import com.Biblioteca.Repository.Empresa.AlmacenBodegaTallerRepository;
import com.Biblioteca.Repository.Empresa.AlmacenRepository;
import com.Biblioteca.Repository.Empresa.BodegaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BodegaService {
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    AlmacenRepository almacenRepository;

    @Autowired
    AlmacenBodegaTallerRepository almacenBodegaTallerRepository;


    public List<BodegaResponse> listAllBodega(){

        List<Bodega> bodega = bodegaRepository.findAll();

        return bodega.stream().map( bodegaRequest->{
           BodegaResponse pcr = new BodegaResponse();
            pcr.setId(bodegaRequest.getAlmacenBodegaTaller().getId());
            pcr.setIdBodega(bodegaRequest.getId());
            pcr.setNombre(bodegaRequest.getAlmacenBodegaTaller().getNombre());
            pcr.setDireccion(bodegaRequest.getAlmacenBodegaTaller().getDireccion());
            pcr.setTelefono(bodegaRequest.getAlmacenBodegaTaller().getTelefono());
            pcr.setCorreo(bodegaRequest.getAlmacenBodegaTaller().getCorreo());
            pcr.setResponsable(bodegaRequest.getAlmacenBodegaTaller().getResponsable());
            pcr.setEstado(bodegaRequest.getAlmacenBodegaTaller().getEstado());
            pcr.setIdSucursal(bodegaRequest.getSucursal().getId());
            pcr.setNombreSucursal(bodegaRequest.getSucursal().getNombre());

            if(bodegaRequest.getAlmacenBodegaTaller().getEstado() == true){
                pcr.setNombreEstado("Activo");
            }

            if(bodegaRequest.getAlmacenBodegaTaller().getEstado() == false){
                pcr.setNombreEstado("Inactivo");
            }


            return pcr;
        }).collect(Collectors.toList());
    }

    public List<BodegaResponse> listAllBodegaPorScucursaById(Long idSucursal){

        List<Bodega> bodega = bodegaRepository.findAllByIdSucursal(idSucursal);

        return bodega.stream().map( bodegaRequest->{
            BodegaResponse pcr = new BodegaResponse();
            pcr.setId(bodegaRequest.getAlmacenBodegaTaller().getId());
            pcr.setIdBodega(bodegaRequest.getId());
            pcr.setNombre(bodegaRequest.getAlmacenBodegaTaller().getNombre());
            pcr.setDireccion(bodegaRequest.getAlmacenBodegaTaller().getDireccion());
            pcr.setTelefono(bodegaRequest.getAlmacenBodegaTaller().getTelefono());
            pcr.setCorreo(bodegaRequest.getAlmacenBodegaTaller().getCorreo());
            pcr.setResponsable(bodegaRequest.getAlmacenBodegaTaller().getResponsable());
            pcr.setEstado(bodegaRequest.getAlmacenBodegaTaller().getEstado());
            pcr.setIdSucursal(bodegaRequest.getSucursal().getId());
            pcr.setNombreSucursal(bodegaRequest.getSucursal().getNombre());

            if(bodegaRequest.getAlmacenBodegaTaller().getEstado() == true){
                pcr.setNombreEstado("Activo");
            }

            if(bodegaRequest.getAlmacenBodegaTaller().getEstado() == false){
                pcr.setNombreEstado("Inactivo");
            }


            return pcr;
        }).collect(Collectors.toList());
    }

    @Transactional
    public AlmacenResponse registrarBodega(BodegaRequest bodegaRequest) {
        Optional<AlmacenBodegaTaller> optional1 = almacenBodegaTallerRepository.findByNombreAndBusqueda(bodegaRequest.getNombre());
         if (!optional1.isPresent()) {
             AlmacenBodegaTaller newAlmBoTaller = new  AlmacenBodegaTaller();

             newAlmBoTaller.setNombre(bodegaRequest.getNombre());
             newAlmBoTaller.setDireccion(bodegaRequest.getDireccion());
             newAlmBoTaller.setTelefono(bodegaRequest.getTelefono());
             newAlmBoTaller.setCorreo(bodegaRequest.getCorreo());
             newAlmBoTaller.setResponsable(bodegaRequest.getResponsable());
             newAlmBoTaller.setEstado(bodegaRequest.getEstado());


           AlmacenBodegaTaller almacenBodegaTaller = almacenBodegaTallerRepository.save(newAlmBoTaller);


            if (almacenBodegaTaller != null) {

                guardarBodega(almacenBodegaTaller.getId(), (bodegaRequest.getIdSucursal()));


return  new AlmacenResponse();

            } else {
                throw new BadRequestException("No se pudo guardar la bodega" + almacenBodegaTaller.getNombre());
            }

        } else {
            throw new BadRequestException("Ya registrado una bodeha con el mismo nombre: " +bodegaRequest.getNombre());
        }




    }

    private boolean guardarBodega(Long id, Long idSucursal) {

        Optional<AlmacenBodegaTaller> optional2 = almacenBodegaTallerRepository.findById(id);
        if (optional2.isPresent()) {

            Optional<Sucursal> optionalSucursal = sucursalRepository.findById(idSucursal);


            if(optionalSucursal.isPresent()){
                AlmacenBodegaTaller ct = optional2.get();
                Sucursal suc = optionalSucursal.get();

                Bodega nuevo = new Bodega();

                nuevo.setAlmacenBodegaTaller(ct);
                nuevo.setSucursal(suc);

                Bodega bodegas = bodegaRepository.save(nuevo);

                if (bodegas != null) {
                    return true;
                } else {
                    throw new BadRequestException("Bodega no registrado");
                }
            } else {
                throw new BadRequestException("no existe una bodega " + id);
            }




        } else {
            throw new BadRequestException("ya existe una bodega " + id);
        }
    }


    public boolean actualizarBodegaconid(BodegaRequest  bodegaRequest) {

        Optional<Bodega> opcionalc = bodegaRepository.findById(bodegaRequest.getIdBodega());


        if (opcionalc.isPresent()) {

            Optional<Sucursal> optionals = sucursalRepository.findById(bodegaRequest.getIdSucursal());

            if(optionals.isPresent()){
                try {

                    opcionalc.get().setSucursal(optionals.get());
                    Bodega c = bodegaRepository.save(opcionalc.get());

                    if (c != null) {
                        guardaractualizacionbodega(c.getAlmacenBodegaTaller().getId(),bodegaRequest.getNombre() ,  bodegaRequest.getDireccion() ,bodegaRequest.getTelefono(), bodegaRequest.getCorreo(), bodegaRequest.getResponsable() ,bodegaRequest.getEstado());
                    } else {
                        throw new BadRequestException("NO SE ACTUALIZO LA BODGA");
                    }
                } catch (Exception ex) {
                    throw new BadRequestException("No ACTUALIZADO" + ex);
                }

            }
            else {
                throw new BadRequestException("NO EXISTE Sucursal CON  ESA ID ");
            }


        } else {
            throw new BadRequestException("NO EXISTE BODEGA CON ID " + bodegaRequest.getId());
        }
        return false;
    }

    private boolean guardaractualizacionbodega(Long idAlmacenBodegaTaller, String nombre, String direccion,String telefono,
                                                 String correo,  String responsable, Boolean estado) {

        Optional<AlmacenBodegaTaller> optionalc = almacenBodegaTallerRepository.findById(idAlmacenBodegaTaller);

        if (optionalc.isPresent()) {
            optionalc.get().setNombre(nombre);
            optionalc.get().setDireccion(direccion);
            optionalc.get().setTelefono(telefono);
            optionalc.get().setCorreo(correo);
            optionalc.get().setResponsable(responsable);
           optionalc.get().setEstado(estado);
            try {
                AlmacenBodegaTaller c = almacenBodegaTallerRepository.save(optionalc.get());
                return true;
            } catch (Exception ex) {
                throw new BadRequestException("NO SE ACTUALIZO LA TABLA TALLER" + ex);
            }
        }
        throw new BadRequestException("NO EXISTE EL TALLER");
    }

}
