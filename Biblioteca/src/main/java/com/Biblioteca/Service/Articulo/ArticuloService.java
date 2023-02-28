package com.Biblioteca.Service.Articulo;

import com.Biblioteca.DTO.Articulo.ArticuloRequest;
import com.Biblioteca.DTO.Articulo.ArticuloResponse;

import com.Biblioteca.DTO.Categoria.CategoriaResponse;
import com.Biblioteca.DTO.Extra.ContarResponse;
import com.Biblioteca.DTO.Extra.MaximoDatoResponse;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Articulo.Articulo;
import com.Biblioteca.Models.Catalogo.Catalogo;
import com.Biblioteca.Models.Categoria.Categoria;

import com.Biblioteca.Repository.Articulo.ArticuloRepository;
import com.Biblioteca.Repository.Catalogo.CatalogoRepository;
import com.Biblioteca.Repository.Categoria.CategoriaRepository;
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
                    newArticulo.setAncho(articuloRequest.getAncho());
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
            response.setAncho(articuloRequest.getAncho());
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

            response.setNombreCatalogo(articuloRequest.getCatalogo().getNombre());
            response.setNombreCategoria(articuloRequest.getCategoria().getNombre());

            if(articuloRequest.getEstadoArticulo() == true){
                response.setNombreEstadoArticulo("Activo");
            }

            if(articuloRequest.getEstadoArticulo() == false){
                response.setNombreEstadoArticulo("Inactivo");
            }


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
            response.setAncho(articuloRequest.get().getAncho());
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


            response.setNombreCatalogo(articuloRequest.get().getCatalogo().getNombre());
            response.setNombreCategoria(articuloRequest.get().getCategoria().getNombre());


            if(articuloRequest.get().getEstadoWeb() == true){
                response.setNombreEstadoWeb("Si");
            }

            if(articuloRequest.get().getEstadoWeb() == false){
                response.setNombreEstadoWeb("No");
            }


            return response;
        }else{
            throw new BadRequestException("No existe un artículo con id seleccionado");
        }

    }



    @Transactional
    public boolean actualizardatosArticulo(ArticuloRequest articuloRequest ){
        Optional<Articulo> articulo = articuloRepository.findById(articuloRequest.getId());

        if(articulo.isPresent()){

            Optional<Catalogo> optionalCatalogo = catalogoRepository.findById(articuloRequest.getIdCatalogo());

            if(optionalCatalogo.isPresent()){

                Optional<Categoria> optionalCategoria = categoriaRepository.findById(articuloRequest.getIdCategoria());

                if(optionalCategoria.isPresent()){
                    articulo.get().setNombre(articuloRequest.getNombre());
                    articulo.get().setDescripcion(articuloRequest.getDescripcion());
                    articulo.get().setStockMinimo(articuloRequest.getStockMinimo());
                    articulo.get().setColor(articuloRequest.getColor());
                    articulo.get().setFoto(articuloRequest.getFoto());
                    articulo.get().setCodigoBarra(articuloRequest.getCodigoBarra());
                    articulo.get().setEstadoArticulo(articuloRequest.getEstadoArticulo());
                    articulo.get().setEstadoWeb(articuloRequest.getEstadoWeb());
                    articulo.get().setCodigoCompra(articuloRequest.getCodigoCompra());
                    articulo.get().setMarca(articuloRequest.getMarca());
                    articulo.get().setVidaUtil(articuloRequest.getVidaUtil());

                    //medida
                    articulo.get().setAlto(articuloRequest.getAlto());
                    articulo.get().setAncho(articuloRequest.getAncho());
                    articulo.get().setProfundidad(articuloRequest.getProfundidad());
                    articulo.get().setPeso(articuloRequest.getPeso());

                    //precio
                    articulo.get().setPrecioCosto(articuloRequest.getPrecioCosto());
                    articulo.get().setIva(articuloRequest.getIva());
                    articulo.get().setPrecioIva(articuloRequest.getPrecioIva());
                    articulo.get().setPrecioStandar(articuloRequest.getPrecioStandar());
                    articulo.get().setMargenProduccion(articuloRequest.getMargenProduccion());
                    articulo.get().setPrecioProduccion(articuloRequest.getPrecioProduccion());
                    articulo.get().setMargenVenta(articuloRequest.getMargenVenta());
                    articulo.get().setPrecioVenta(articuloRequest.getPrecioVenta());
                    articulo.get().setPrecioFinal(articuloRequest.getPrecioFinal());

                    //heredado

                    articulo.get().setCategoria(optionalCategoria.get());
                    articulo.get().setCatalogo(optionalCatalogo.get());

                    try{
                        articuloRepository.save(articulo.get());
                        return true;
                    }catch (Exception ex) {
                        throw new BadRequestException("No se actualizo" + ex);
                    }
                }else {
                    throw new BadRequestException("No existe una categoria   con id "+articuloRequest.getIdCategoria() );
                }

            }else {
                throw new BadRequestException("No existe un catalogo  con id "+articuloRequest.getIdCatalogo() );
            }


        } else {
            throw new BadRequestException("No existe un articulo  con id "+articuloRequest.getId() );
        }
    }





    public MaximoDatoResponse CapturarMaximoCodigoBarraById (Long id){

        MaximoDatoResponse response = new MaximoDatoResponse();


        String valorFinal = "";
        if(count1(id) == BigInteger.valueOf(0)){
            System.out.println("Valor cero");
            valorFinal = inicate(id) + "00001";

        }else{

            String palabra, patron , numerooini;
            int i;

            palabra = max1(id);
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

    private boolean getNombre(String nombre) {
        return  articuloRepository.existsByNombre(nombre);
    }



    @PersistenceContext
    private EntityManager entityManager;


    public BigInteger count1(Long id) {
        Query nativeQuery = entityManager.createNativeQuery("select count(id) from articulo where categoria_id =?");
        nativeQuery.setParameter(1, id);
        return (BigInteger) nativeQuery.getSingleResult();
    }

    public String max1(Long id) {
        Query nativeQuery = entityManager.createNativeQuery("select MAX(codigo_barra) from articulo where categoria_id =?");
        nativeQuery.setParameter(1, id);
        return (String) nativeQuery.getSingleResult();
    }

    public String inicate(Long id) {
        Query nativeQuery = entityManager.createNativeQuery("SELECT incial_codigo FROM categoria WHERE id =?");
        nativeQuery.setParameter(1, id);
        return (String) nativeQuery.getSingleResult();
    }


}
