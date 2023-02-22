package com.Biblioteca.Service.Empresa;

import com.Biblioteca.DTO.empresa.sucursales.*;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Repository.Empresa.SucursalRepository;
import com.Biblioteca.Models.Empresa.Sucursales.AlmacenBodegaTaller;
import com.Biblioteca.Models.Empresa.Sucursales.Taller;
import com.Biblioteca.Repository.Empresa.AlmacenBodegaTallerRepository;
import com.Biblioteca.Repository.Empresa.TallerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TallerService {
    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    TallerRepository tallerRepository;

    @Autowired
    AlmacenBodegaTallerRepository almacenBodegaTallerRepository;


    public List<TallerResponse> listAllTaller(){

        List<Taller> taller = tallerRepository.findAll();



        return taller.stream().map( tallerRequest->{
           TallerResponse pcr = new TallerResponse();

            pcr.setId(tallerRequest.getAlmacenBodegaTaller().getId());
            pcr.setIdTaller(tallerRequest.getId());
            pcr.setNombre(tallerRequest.getAlmacenBodegaTaller().getNombre());
            pcr.setDireccion(tallerRequest.getAlmacenBodegaTaller().getDireccion());
            pcr.setTelefono(tallerRequest.getAlmacenBodegaTaller().getTelefono());
            pcr.setCorreo(tallerRequest.getAlmacenBodegaTaller().getCorreo());
            pcr.setResponsable(tallerRequest.getAlmacenBodegaTaller().getResponsable());
            pcr.setEstado(tallerRequest.getAlmacenBodegaTaller().getEstado());
            pcr.setIdSucursal(tallerRequest.getSucursal().getId());
            pcr.setNombreSucursal(tallerRequest.getSucursal().getNombre());

            return pcr;
        }).collect(Collectors.toList());
    }

    @Transactional
    public AlmacenResponse registrarTaller(TallerRequest tallerRequest ) {
        Optional<AlmacenBodegaTaller> optional1 = almacenBodegaTallerRepository.findByNombreAndBusqueda(tallerRequest.getNombre());


        if (!optional1.isPresent()) {
             AlmacenBodegaTaller newAlmBoTaller = new  AlmacenBodegaTaller();

             newAlmBoTaller.setNombre(tallerRequest.getNombre());
             newAlmBoTaller.setDireccion(tallerRequest.getDireccion());
             newAlmBoTaller.setTelefono(tallerRequest.getTelefono());
             newAlmBoTaller.setCorreo(tallerRequest.getCorreo());
             newAlmBoTaller.setResponsable(tallerRequest.getResponsable());
             newAlmBoTaller.setEstado(tallerRequest.getEstado());


           AlmacenBodegaTaller almacenBodegaTaller = almacenBodegaTallerRepository.save(newAlmBoTaller);


            if (almacenBodegaTaller != null) {

                guardarTaller(almacenBodegaTaller.getId(), (tallerRequest.getIdSucursal()));


return  new AlmacenResponse();

            } else {
                throw new BadRequestException("No se pudo guardar el Taller" + almacenBodegaTaller.getNombre());
            }

        } else {
            throw new BadRequestException("Ya registrado una bodeha con el mismo nombre: " +tallerRequest.getNombre());
        }




    }

    private boolean guardarTaller(Long id, Long idSucursal) {

        Optional<AlmacenBodegaTaller> optional2 = almacenBodegaTallerRepository.findById(id);
        if (optional2.isPresent()) {

            Optional<Sucursal> optionalSucursal = sucursalRepository.findById(idSucursal);


            if(optionalSucursal.isPresent()){
                AlmacenBodegaTaller ct = optional2.get();
                Sucursal suc = optionalSucursal.get();

                Taller nuevo = new Taller();

                nuevo.setAlmacenBodegaTaller(ct);
                nuevo.setSucursal(suc);

                Taller talleres = tallerRepository.save(nuevo);

                if (talleres != null) {
                    return true;
                } else {
                    throw new BadRequestException("Taller no registrado");
                }
            } else {
                throw new BadRequestException("no existe un taller " + id);
            }




        } else {
            throw new BadRequestException("ya existe un taller " + id);
        }
    }


    public boolean actualizarTallerconid(TallerRequest tallerRequest ) {

        Optional<Taller> opcionalc = tallerRepository.findById(tallerRequest.getIdTaller());


        if (opcionalc.isPresent()) {

            Optional<Sucursal> optionals = sucursalRepository.findById(tallerRequest.getIdSucursal());

            if(optionals.isPresent()){
                try {

                    opcionalc.get().setSucursal(optionals.get());
                    Taller c = tallerRepository.save(opcionalc.get());

                    if (c != null) {
                        guardaractualizacionTaller(c.getAlmacenBodegaTaller().getId(),tallerRequest.getNombre() ,  tallerRequest.getDireccion() ,tallerRequest.getTelefono(), tallerRequest.getCorreo(), tallerRequest.getResponsable() ,tallerRequest.getEstado());
                    } else {
                        throw new BadRequestException("NO SE ACTUALIZO EL TALLER");
                    }
                } catch (Exception ex) {
                    throw new BadRequestException("No ACTUALIZADO" + ex);
                }

            }
            else {
                throw new BadRequestException("NO EXISTE Sucursal CON  ESA ID ");
            }


        } else {
            throw new BadRequestException("NO EXISTE TALLER CON ID " + tallerRequest.getId());
        }
        return false;
    }

    private boolean guardaractualizacionTaller(Long idAlmacenBodegaTaller, String nombre, String direccion,String telefono,
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
