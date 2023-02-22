package com.Biblioteca.Service.Empresa;

import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.DTO.empresa.sucursales.AlmacenRequest;
import com.Biblioteca.DTO.empresa.sucursales.AlmacenResponse;
import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Repository.Empresa.SucursalRepository;
import com.Biblioteca.Models.Empresa.Sucursales.Almacen;
import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import com.Biblioteca.Repository.Empresa.AlmacenBodegaTallerRepository;
import com.Biblioteca.Repository.Empresa.AlmacenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AlmacenService {
    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    AlmacenRepository almacenRepository;

    @Autowired
    AlmacenBodegaTallerRepository almacenBodegaTallerRepository;


    public List<AlmacenResponse > listAllAlmacen(){

        List<Almacen> almacen = almacenRepository.findAll();

        return almacen.stream().map( almacenRequest->{
            AlmacenResponse pcr = new AlmacenResponse();
            pcr.setId(almacenRequest.getAlmacenBodegaTaller().getId());
            pcr.setIdAlmacen(almacenRequest.getId());
            pcr.setNombre(almacenRequest.getAlmacenBodegaTaller().getNombre());
            pcr.setDireccion(almacenRequest.getAlmacenBodegaTaller().getDireccion());
            pcr.setTelefono(almacenRequest.getAlmacenBodegaTaller().getTelefono());
            pcr.setCorreo(almacenRequest.getAlmacenBodegaTaller().getCorreo());
            pcr.setResponsable(almacenRequest.getAlmacenBodegaTaller().getResponsable());
            pcr.setEstado(almacenRequest.getAlmacenBodegaTaller().getEstado());
            pcr.setIdSucursal(almacenRequest.getSucursal().getId());
            pcr.setNombreSucursal(almacenRequest.getSucursal().getNombre());

            return pcr;
        }).collect(Collectors.toList());
    }

    @Transactional
    public AlmacenResponse registrarAlmacen(AlmacenRequest almacenRequest) {
        Optional<AlmacenBodegaTaller> optional1 = almacenBodegaTallerRepository.findByNombreAndBusqueda(almacenRequest.getNombre());
         if (!optional1.isPresent()) {
             AlmacenBodegaTaller newAlmBoTaller = new  AlmacenBodegaTaller();

             newAlmBoTaller.setNombre(almacenRequest.getNombre());
             newAlmBoTaller.setDireccion(almacenRequest.getDireccion());
             newAlmBoTaller.setTelefono(almacenRequest.getTelefono());
             newAlmBoTaller.setCorreo(almacenRequest.getCorreo());
             newAlmBoTaller.setResponsable(almacenRequest.getResponsable());
             newAlmBoTaller.setEstado(almacenRequest.getEstado());


           AlmacenBodegaTaller almacenBodegaTaller = almacenBodegaTallerRepository.save(newAlmBoTaller);


            if (almacenBodegaTaller != null) {

                guardarAlmacen(almacenBodegaTaller.getId(), (almacenRequest.getIdSucursal()));


return  new AlmacenResponse();

            } else {
                throw new BadRequestException("No se pudo guardar el almaceno" + almacenBodegaTaller.getNombre());
            }

        } else {
            throw new BadRequestException("Ya registrado un almacen con el mismo nombre: " +almacenRequest.getNombre());
        }




    }

    private boolean guardarAlmacen(Long id, Long idSucursal) {

        Optional<AlmacenBodegaTaller> optional2 = almacenBodegaTallerRepository.findById(id);
        if (optional2.isPresent()) {

            Optional<Sucursal> optionalSucursal = sucursalRepository.findById(idSucursal);


            if(optionalSucursal.isPresent()){
                AlmacenBodegaTaller ct = optional2.get();
                Sucursal suc = optionalSucursal.get();

                Almacen nuevo = new Almacen();

                nuevo.setAlmacenBodegaTaller(ct);
                nuevo.setSucursal(suc);

                Almacen almacens = almacenRepository.save(nuevo);

                if (almacens != null) {
                    return true;
                } else {
                    throw new BadRequestException("Almacen no registrado");
                }
            } else {
                throw new BadRequestException("no existe una sucursal " + id);
            }




        } else {
            throw new BadRequestException("ya existe un curso " + id);
        }
    }


    public boolean actualizarAlmacenconid(AlmacenRequest almacenRequest) {

        Optional<Almacen> opcionalc = almacenRepository.findById(almacenRequest.getIdAlmacen());


        if (opcionalc.isPresent()) {

            Optional<Sucursal> optionals = sucursalRepository.findById(almacenRequest.getIdSucursal());

            if(optionals.isPresent()){
                try {

                    opcionalc.get().setSucursal(optionals.get());
                    Almacen c = almacenRepository.save(opcionalc.get());

                    if (c != null) {
                        guardaractualizacionalmacen(c.getAlmacenBodegaTaller().getId(),almacenRequest.getNombre() ,  almacenRequest.getDireccion() ,almacenRequest.getTelefono(), almacenRequest.getCorreo(), almacenRequest.getResponsable() ,almacenRequest.getEstado());
                    } else {
                        throw new BadRequestException("NO SE ACTUALIZO EL ALMACEN");
                    }
                } catch (Exception ex) {
                    throw new BadRequestException("No ACTUALIZADO" + ex);
                }

            }
            else {
                throw new BadRequestException("NO EXISTE Sucursal CON  ESA ID ");
            }


        } else {
            throw new BadRequestException("NO EXISTE EL TALLER CON ID " + almacenRequest.getId());
        }
        return false;
    }

    private boolean guardaractualizacionalmacen(Long idAlmacenBodegaTaller, String nombre, String direccion,String telefono,
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
