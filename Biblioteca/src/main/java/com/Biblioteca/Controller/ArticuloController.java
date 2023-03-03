package com.Biblioteca.Controller;

import com.Biblioteca.DTO.Articulo.ArticuloProveedorRequest;
import com.Biblioteca.DTO.Articulo.ArticuloProveedorResponse;
import com.Biblioteca.DTO.Articulo.ArticuloRequest;
import com.Biblioteca.DTO.Articulo.ArticuloResponse;
import com.Biblioteca.DTO.Extra.ContarResponse;
import com.Biblioteca.DTO.Extra.MaximoDatoResponse;
import com.Biblioteca.Exceptions.Mensaje;
import com.Biblioteca.Service.Articulo.ArticuloProveedorService;
import com.Biblioteca.Service.Articulo.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/articulo")
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @Autowired
    ArticuloProveedorService articuloProveedorService;


    @GetMapping("/allArticulo")
    public ResponseEntity<List<ArticuloResponse>> allArticulo(){

        List<ArticuloResponse> allArticulos = articuloService.listAllArticulo();
        return new ResponseEntity<>(allArticulos, HttpStatus.OK);
    }





    @GetMapping("/allBylistaArticulo/{id}")
    public ResponseEntity<ArticuloResponse > listUsuarioById(@PathVariable Long id){
        ArticuloResponse info = articuloService.articulosById(id);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }


    @GetMapping("/allArticuloProveedor/{articulo_id}")
    public ResponseEntity<List<ArticuloProveedorResponse>> allArticuloProveedor(@PathVariable Long articulo_id){
        List<ArticuloProveedorResponse> allArticulosProveedr = articuloProveedorService.listAllArticuloProveedor(articulo_id);
        return new ResponseEntity<>(allArticulosProveedr, HttpStatus.OK);
    }


    @GetMapping("/allByMaximoCodigoBarra/{categoria_id}")
    public ResponseEntity<MaximoDatoResponse> ContarMaximoCodigoBarraById(@PathVariable Long categoria_id){
        MaximoDatoResponse  info = articuloService.CapturarMaximoCodigoBarraById(categoria_id);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }


    @PostMapping("/registrarArticulo")
    public ResponseEntity<?> registroArticulo(@RequestBody ArticuloRequest request){

        return new ResponseEntity<>(articuloService.regitrarArticulo(request), HttpStatus.OK);
    }

    @PostMapping("/registrarArticuloProveedor")
    public ResponseEntity<?> registroArticuloProveedor(@RequestBody ArticuloProveedorRequest request){

        return new ResponseEntity<>(articuloProveedorService.regitrarArticuloProveedor(request), HttpStatus.OK);
    }


    @PutMapping("/updateArticulo")
    public ResponseEntity<?> updateArticulo(@RequestBody ArticuloRequest articuloRequest){
        articuloService.actualizardatosArticulo(articuloRequest);
        return new ResponseEntity(new Mensaje("Artículo Actualizado"), HttpStatus.OK);
    }

}
