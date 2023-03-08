package com.Biblioteca.Controller;

import com.Biblioteca.DTO.Extra.MaximoDatoResponse;
import com.Biblioteca.DTO.Servicio.ServicioRequest;
import com.Biblioteca.DTO.Servicio.ServicioResponse;
import com.Biblioteca.DTO.Transporte.TransporteRequest;
import com.Biblioteca.DTO.Transporte.TransporteResponse;
import com.Biblioteca.Exceptions.Mensaje;
import com.Biblioteca.Service.Servicio.ServicioService;
import com.Biblioteca.Service.Trasporte.TrasporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/trasporte")
public class TransporteController {

    @Autowired
    TrasporteService trasporteService;

    @PostMapping("/registrarTransporte")
    public ResponseEntity<?> registroTransporte(@RequestBody TransporteRequest request){
        return new ResponseEntity<>(trasporteService.regitrarTransporte(request) , HttpStatus.OK);
    }

    @GetMapping("/allTrasporte")
    public ResponseEntity<List<TransporteResponse>> allTransporte(){
        List<TransporteResponse> allTransportes = trasporteService.listAllTrasporte();
        return new ResponseEntity<>(allTransportes, HttpStatus.OK);
    }

    @GetMapping("/allBylistaTrasporte/{id}")
    public ResponseEntity<TransporteResponse> listTransporteById(@PathVariable Long id){
        TransporteResponse info = trasporteService.transporteById(id);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }



    @PutMapping("/updateTrasporte")
    public ResponseEntity<?> updateTransporte(@RequestBody TransporteRequest transporteRequest ){
        trasporteService.actualizardatosTransporte(transporteRequest);
        return new ResponseEntity(new Mensaje("Transporte Actualizado"), HttpStatus.OK);
    }
}
