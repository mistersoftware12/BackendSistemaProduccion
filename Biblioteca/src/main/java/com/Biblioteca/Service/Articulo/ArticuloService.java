package com.Biblioteca.Service.Articulo;

import com.Biblioteca.DTO.Articulo.ArticuloRequest;
import com.Biblioteca.DTO.Articulo.ArticuloResponse;
import com.Biblioteca.DTO.Catalogo.CatalogoRequest;
import com.Biblioteca.DTO.Persona.PersonaUsuarioResponse;
import com.Biblioteca.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Articulo.Articulo;
import com.Biblioteca.Models.Catalogo.Catalogo;
import com.Biblioteca.Models.Categoria.Categoria;
import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Models.Persona.Persona;
import com.Biblioteca.Models.Persona.Usuario;
import com.Biblioteca.Repository.Articulo.ArticuloRepository;
import com.Biblioteca.Repository.Catalogo.CatalogoRepository;
import com.Biblioteca.Repository.Categoria.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    CatalogoRepository catalogoRepository;


    public boolean regitrarArticulo (ArticuloRequest articuloRequest){

        if(!getNombre(articuloRequest.getNombre())){

            Optional<Catalogo> optionalCatalogo = catalogoRepository.findById(articuloRequest.getIdCatalogo());

            if(optionalCatalogo.isPresent()){

               // Catalogo ca= optionalCatalogo.get();
                Optional<Categoria> optionalCategoria = categoriaRepository.findById(articuloRequest.getIdCategoria());

                if(optionalCategoria.isPresent()){

                   // Categoria cate = optionalCategoria.get();

                    Articulo newArticulo = new Articulo();
                    newArticulo.setNombre(articuloRequest.getNombre());
                    newArticulo.setDescripcion(articuloRequest.getDescripcion());
                    newArticulo.setStockMinimo(articuloRequest.getStockMinimo());
                    newArticulo.setColor(articuloRequest.getColor());
                    newArticulo.setFoto(articuloRequest.getFoto());
                    newArticulo.setCodigoBarra(articuloRequest.getCodigoBarra());
                    newArticulo.setEstadoArticulo(articuloRequest.getEstadoArticulo());
                    newArticulo.setEstadoWeb(articuloRequest.getEstadoWeb());
                    newArticulo.setCodigoCompra(articuloRequest.getCodigoCompra());
                    newArticulo.setMarca(articuloRequest.getMarca());
                    newArticulo.setVidaUtil(articuloRequest.getVidaUtil());

                    //medida
                    newArticulo.setAlto(articuloRequest.getAlto());
                    newArticulo.setAlto(articuloRequest.getAncho());
                    newArticulo.setProfundidad(articuloRequest.getProfundidad());
                    newArticulo.setPeso(articuloRequest.getPeso());

                    //precio
                    newArticulo.setPrecioCosto(articuloRequest.getPrecioCosto());
                    newArticulo.setIva(articuloRequest.getIva());
                    newArticulo.setPrecioIva(articuloRequest.getPrecioIva());
                    newArticulo.setPrecioStandar(articuloRequest.getPrecioStandar());
                    newArticulo.setMargenProduccion(articuloRequest.getMargenProduccion());
                    newArticulo.setPrecioProduccion(articuloRequest.getPrecioProduccion());
                    newArticulo.setMargenVenta(articuloRequest.getMargenVenta());
                    newArticulo.setPrecioVenta(articuloRequest.getPrecioVenta());
                    newArticulo.setPrecioFinal(articuloRequest.getPrecioFinal());


                    newArticulo.setCategoria(optionalCategoria.get());
                    newArticulo.setCatalogo(optionalCatalogo.get());


                    try {
                        articuloRepository.save(newArticulo);
                        return true;
                    }catch (Exception e){
                        throw new BadRequestException("No se registró el articulo" +e);
                    }

                }else{
                    throw  new BadRequestException("No exiete el catálogo selecciono");
                }

            }else{
                throw new BadRequestException("No existe la categoría seleccionada");
            }



        }else{
            throw new BadRequestException("Ya existe un articulo con ese nombre");
        }


    }


    public List<ArticuloResponse> listAllArticulo() {

        List<Articulo> articulo = articuloRepository.findAll();
        return articulo.stream().map(articuloRequest->{
            ArticuloResponse response = new ArticuloResponse();
            response.setId(articuloRequest.getId());
            response.setNombre(articuloRequest.getNombre());
            response.setDescripcion(articuloRequest.getDescripcion());
            response.setStockMinimo(articuloRequest.getStockMinimo());
            response.setColor(articuloRequest.getColor());
            response.setFoto(articuloRequest.getFoto());
            response.setCodigoBarra(articuloRequest.getCodigoBarra());
            response.setEstadoArticulo(articuloRequest.getEstadoArticulo());
            response.setEstadoWeb(articuloRequest.getEstadoWeb());
            response.setCodigoCompra(articuloRequest.getCodigoCompra());
            response.setMarca(articuloRequest.getMarca());
            response.setVidaUtil(articuloRequest.getVidaUtil());

            //medida
            response.setAlto(articuloRequest.getAlto());
            response.setAlto(articuloRequest.getAncho());
            response.setProfundidad(articuloRequest.getProfundidad());
            response.setPeso(articuloRequest.getPeso());

            //precio
            response.setPrecioCosto(articuloRequest.getPrecioCosto());
            response.setIva(articuloRequest.getIva());
            response.setPrecioIva(articuloRequest.getPrecioIva());
            response.setPrecioStandar(articuloRequest.getPrecioStandar());
            response.setMargenProduccion(articuloRequest.getMargenProduccion());
            response.setPrecioProduccion(articuloRequest.getPrecioProduccion());
            response.setMargenVenta(articuloRequest.getMargenVenta());
            response.setPrecioVenta(articuloRequest.getPrecioVenta());
            response.setPrecioFinal(articuloRequest.getPrecioFinal());

            //heredado

            response.setIdCategoria(articuloRequest.getCategoria().getId());
            response.setIdCatalogo(articuloRequest.getCatalogo().getId());

            return response;
        }).collect(Collectors.toList());
    }


    public  ArticuloResponse articulosById(Long id){
        ArticuloResponse response = new ArticuloResponse();
        Optional<Articulo> articuloRequest = articuloRepository.findById(id);

        if(articuloRequest.isPresent()){
            response.setId(articuloRequest.get().getId());
            response.setNombre(articuloRequest.get().getNombre());
            response.setDescripcion(articuloRequest.get().getDescripcion());
            response.setStockMinimo(articuloRequest.get().getStockMinimo());
            response.setColor(articuloRequest.get().getColor());
            response.setFoto(articuloRequest.get().getFoto());
            response.setCodigoBarra(articuloRequest.get().getCodigoBarra());
            response.setEstadoArticulo(articuloRequest.get().getEstadoArticulo());
            response.setEstadoWeb(articuloRequest.get().getEstadoWeb());
            response.setCodigoCompra(articuloRequest.get().getCodigoCompra());
            response.setMarca(articuloRequest.get().getMarca());
            response.setVidaUtil(articuloRequest.get().getVidaUtil());

            //medida
            response.setAlto(articuloRequest.get().getAlto());
            response.setAlto(articuloRequest.get().getAncho());
            response.setProfundidad(articuloRequest.get().getProfundidad());
            response.setPeso(articuloRequest.get().getPeso());

            //precio
            response.setPrecioCosto(articuloRequest.get().getPrecioCosto());
            response.setIva(articuloRequest.get().getIva());
            response.setPrecioIva(articuloRequest.get().getPrecioIva());
            response.setPrecioStandar(articuloRequest.get().getPrecioStandar());
            response.setMargenProduccion(articuloRequest.get().getMargenProduccion());
            response.setPrecioProduccion(articuloRequest.get().getPrecioProduccion());
            response.setMargenVenta(articuloRequest.get().getMargenVenta());
            response.setPrecioVenta(articuloRequest.get().getPrecioVenta());
            response.setPrecioFinal(articuloRequest.get().getPrecioFinal());

            //heredado

            response.setIdCategoria(articuloRequest.get().getCategoria().getId());
            response.setIdCatalogo(articuloRequest.get().getCatalogo().getId());

            return response;
        }else{
            throw new BadRequestException("No existe un artículo con id seleccionado");
        }

/*
        PersonaUsuarioResponse response = new PersonaUsuarioResponse();
        Optional<Persona> persona = personaRepository.findByCedula(cedula);
        if(persona.isPresent()) {
            Optional<Usuario> user = usuarioRepository.findByPersona(persona.get());
            if(user.isPresent()) {
                response.setId(persona.get().getId());
                response.setIdUsuario(user.get().getId());
                response.setCedula(user.get().getPersona().getCedula());
                response.setNombres(user.get().getPersona().getNombres());
                response.setApellidos(user.get().getPersona().getApellidos());
                response.setTelefono(user.get().getPersona().getTelefono());
                response.setEmail(user.get().getPersona().getEmail());
                response.setIdRol(user.get().getRoles().getId());
                response.setFechaNacimiento(user.get().getPersona().getFechaNacimiento());
                response.setDireccion(user.get().getPersona().getDireccion());
                response.setIdSucursal(user.get().getSucursal().getId());

                return response;
            }else{
                throw new BadRequestException("No existe un articulo con id" +cedula);
            }
        }else{
            throw new BadRequestException("No existe un cliente vinculado a esa persona");
        }*/
    }



    private boolean getNombre(String nombre) {
        return  articuloRepository.existsByNombre(nombre);
    }

}
