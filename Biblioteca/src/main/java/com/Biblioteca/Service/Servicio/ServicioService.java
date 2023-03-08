package com.Biblioteca.Service.Servicio;

import com.Biblioteca.DTO.Articulo.ArticuloResponse;
import com.Biblioteca.DTO.Extra.MaximoDatoResponse;
import com.Biblioteca.DTO.Servicio.ServicioRequest;
import com.Biblioteca.DTO.Servicio.ServicioResponse;
import com.Biblioteca.DTO.empresa.sucursales.SucursalRequest;
import com.Biblioteca.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Articulo.Articulo;
import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Models.Servicio.Servicio;
import com.Biblioteca.Repository.Empresa.SucursalRepository;
import com.Biblioteca.Repository.Servicio.ServicioRepository;
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
public class ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;


    public boolean regitrarServicio (ServicioRequest servicioRequest){


        if(!getNombre(servicioRequest.getNombre())){
            Servicio newServicio = new Servicio();

            newServicio.setNombre(servicioRequest.getNombre());
            newServicio.setEstado(servicioRequest.isEstado());
            newServicio.setCodigo_barra(servicioRequest.getCodigo_barra());

            //precio
            newServicio.setPrecioCosto(servicioRequest.getPrecioCosto());
            newServicio.setIva(servicioRequest.getIva());
            newServicio.setPrecioIva(servicioRequest.getPrecioIva());
            newServicio.setPrecioStandar(servicioRequest.getPrecioStandar());
            newServicio.setMargenProduccion(servicioRequest.getMargenProduccion());
            newServicio.setPrecioProduccion(servicioRequest.getPrecioProduccion());
            newServicio.setMargenVenta(servicioRequest.getMargenVenta());
            newServicio.setPrecioVenta(servicioRequest.getPrecioVenta());
            newServicio.setPrecioFinal(servicioRequest.getPrecioFinal());

            try {
                servicioRepository.save(newServicio);
                return true;
            }catch (Exception e){
                throw new BadRequestException("No se registró el servicio" +e);
            }
        }else {
            throw new BadRequestException("Ya existe una servicio con ese nombre");
        }


    }


    public List<ServicioResponse> listAllServicio() {

        List<Servicio> servicios = servicioRepository.findAll();

        return servicios.stream().map(servicioRequest->{
            ServicioResponse response = new ServicioResponse();

            response.setId(servicioRequest.getId());
            response.setNombre(servicioRequest.getNombre());
            response.setEstado(servicioRequest.isEstado());
            response.setCodigo_barra(servicioRequest.getCodigo_barra());


            //precio
            response.setPrecioCosto(servicioRequest.getPrecioCosto());
            response.setIva(servicioRequest.getIva());
            response.setPrecioIva(servicioRequest.getPrecioIva());
            response.setPrecioStandar(servicioRequest.getPrecioStandar());
            response.setMargenProduccion(servicioRequest.getMargenProduccion());
            response.setPrecioProduccion(servicioRequest.getPrecioProduccion());
            response.setMargenVenta(servicioRequest.getMargenVenta());
            response.setPrecioVenta(servicioRequest.getPrecioVenta());
            response.setPrecioFinal(servicioRequest.getPrecioFinal());


            if(servicioRequest.isEstado() == true){
                response.setNombreEstado("Activo");
            }

            if(servicioRequest.isEstado() == false){
                response.setNombreEstado("Inactivo");
            }

            return response;
        }).collect(Collectors.toList());
    }


    public ServicioResponse servicioById(Long id){

        ServicioResponse response = new ServicioResponse();

        Optional<Servicio> servicioRequest = servicioRepository.findById(id);

        if(servicioRequest.isPresent()){

            response.setId(servicioRequest.get().getId());
            response.setNombre(servicioRequest.get().getNombre());
            response.setEstado(servicioRequest.get().isEstado());
            response.setCodigo_barra(servicioRequest.get().getCodigo_barra());


            //precio
            response.setPrecioCosto(servicioRequest.get().getPrecioCosto());
            response.setIva(servicioRequest.get().getIva());
            response.setPrecioIva(servicioRequest.get().getPrecioIva());
            response.setPrecioStandar(servicioRequest.get().getPrecioStandar());
            response.setMargenProduccion(servicioRequest.get().getMargenProduccion());
            response.setPrecioProduccion(servicioRequest.get().getPrecioProduccion());
            response.setMargenVenta(servicioRequest.get().getMargenVenta());
            response.setPrecioVenta(servicioRequest.get().getPrecioVenta());
            response.setPrecioFinal(servicioRequest.get().getPrecioFinal());




            if(servicioRequest.get().isEstado() == true){
                response.setNombreEstado("Si");
            }

            if(servicioRequest.get().isEstado() == false){
                response.setNombreEstado("No");
            }


            return response;
        }else{
            throw new BadRequestException("No existe un servicio con id seleccionado");
        }

    }

    @Transactional
    public boolean actualizardatosServicio(ServicioRequest servicioRequest ){

        Optional<Servicio> servicio = servicioRepository.findById(servicioRequest.getId());


        if(servicio.isPresent()){

            servicio.get().setNombre(servicioRequest.getNombre());

            servicio.get().setNombre(servicioRequest.getNombre());
            servicio.get().setEstado(servicioRequest.isEstado());
            servicio.get().setCodigo_barra(servicioRequest.getCodigo_barra());

            //precio
            servicio.get().setPrecioCosto(servicioRequest.getPrecioCosto());
            servicio.get().setIva(servicioRequest.getIva());
            servicio.get().setPrecioIva(servicioRequest.getPrecioIva());
            servicio.get().setPrecioStandar(servicioRequest.getPrecioStandar());
            servicio.get().setMargenProduccion(servicioRequest.getMargenProduccion());
            servicio.get().setPrecioProduccion(servicioRequest.getPrecioProduccion());
            servicio.get().setMargenVenta(servicioRequest.getMargenVenta());
            servicio.get().setPrecioVenta(servicioRequest.getPrecioVenta());
            servicio.get().setPrecioFinal(servicioRequest.getPrecioFinal());


            try{
                servicioRepository.save(servicio.get());
                return true;
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizo" + ex);
            }
        } else {
            throw new BadRequestException("No existe un servicio con id "+servicioRequest.getId() );
        }
    }


    public MaximoDatoResponse CapturarMaximoCodigoBarraById (){

        MaximoDatoResponse response = new MaximoDatoResponse();


        String valorFinal = "";
        if(count1() == BigInteger.valueOf(0)){
            System.out.println("Valor cero");
            valorFinal =  "SERV00001";

        }else{

            String palabra, patron , numerooini;
            int i;

            palabra = max1();
            i = 0;
            patron = "";
            numerooini = "";

            while (i < palabra.length()) {

                if(i < 4){
                    patron = patron+ palabra.charAt(i) ;
                }

                if(i>3){
                    numerooini = numerooini + palabra.charAt(i);
                }

                i++;
            }

            i = Integer.parseInt(numerooini) +1;
            String valoras = String.valueOf(i);

            if(valoras.length() == 1){
                valoras = "0000"+valoras;
            }

            if(valoras.length() == 2){
                valoras = "000"+valoras;
            }
            if(valoras.length() == 3){
                valoras = "00"+valoras;
            }
            if(valoras.length() == 4){
                valoras = "0"+valoras;
            }
            if(valoras.length() == 5){
                valoras = valoras;
            }


            valorFinal = patron +valoras;


        }

        response.setMaximoDato(String.valueOf(valorFinal));

        return response;

    }

    @PersistenceContext
    private EntityManager entityManager;
    public BigInteger count1() {
        Query nativeQuery = entityManager.createNativeQuery("select count(id) from servicio");

        return (BigInteger) nativeQuery.getSingleResult();
    }


    public String max1() {
        Query nativeQuery = entityManager.createNativeQuery("select MAX(codigo_barra) from servicio");

        return (String) nativeQuery.getSingleResult();
    }


    private boolean getNombre(String nombre) {
        return  servicioRepository.existsByNombre(nombre);
    }


}
