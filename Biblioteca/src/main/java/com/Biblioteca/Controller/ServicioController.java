package com.Biblioteca.Controller;

import com.Biblioteca.DTO.Articulo.ArticuloResponse;
import com.Biblioteca.DTO.Extra.MaximoDatoResponse;
import com.Biblioteca.DTO.Servicio.ServicioRequest;
import com.Biblioteca.DTO.Servicio.ServicioResponse;
import com.Biblioteca.DTO.empresa.sucursales.AlmacenRequest;
import com.Biblioteca.DTO.empresa.sucursales.SucursalRequest;
import com.Biblioteca.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Exceptions.Mensaje;
import com.Biblioteca.Service.Servicio.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/servicio")
public class ServicioController {

    @Autowired
    ServicioService servicioService;

    @PostMapping("/registrarServicio")
    public ResponseEntity<?> registroServicio(@RequestBody ServicioRequest  request){
        return new ResponseEntity<>(servicioService.regitrarServicio(request) , HttpStatus.OK);
    }

    @GetMapping("/allServicio")
    public ResponseEntity<List<ServicioResponse>> allServicio(){
        List<ServicioResponse> allServicios = servicioService.listAllServicio();
        return new ResponseEntity<>(allServicios, HttpStatus.OK);
    }

    @GetMapping("/allBylistaServicio/{id}")
    public ResponseEntity<ServicioResponse> listServicioById(@PathVariable Long id){
        ServicioResponse info = servicioService.servicioById(id);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("/allByMaximoCodigoBarra")
    public ResponseEntity<MaximoDatoResponse> ContarMaximoCodigoBarraById(){
        MaximoDatoResponse  info = servicioService.CapturarMaximoCodigoBarraById();
        return new ResponseEntity<>(info, HttpStatus.OK);
    }


    @PutMapping("/updateServicio")
    public ResponseEntity<?> updateServicio(@RequestBody ServicioRequest servicioRequest){
        servicioService.actualizardatosServicio(servicioRequest);
        return new ResponseEntity(new Mensaje("Servicio Actualizado"), HttpStatus.OK);
    }
}
