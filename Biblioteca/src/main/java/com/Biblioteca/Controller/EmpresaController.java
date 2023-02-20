package com.Biblioteca.Controller;

import com.Biblioteca.Exceptions.DTO.Persona.PersonaUsuarioResponse;
import com.Biblioteca.Exceptions.DTO.empresa.sucursales.AlmacenRequest;
import com.Biblioteca.Exceptions.DTO.empresa.sucursales.AlmacenResponse;
import com.Biblioteca.Exceptions.DTO.empresa.sucursales.SucursalRequest;
import com.Biblioteca.Exceptions.DTO.empresa.sucursales.SucursalResponse;
import com.Biblioteca.Exceptions.Mensaje;
import com.Biblioteca.Service.Empresa.AlmacenService;
import com.Biblioteca.Service.Empresa.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {

@Autowired
    AlmacenService almacenService;

@Autowired
    SucursalService sucursalService;


    @PostMapping("/registrarAlmacen")
    public ResponseEntity<?> registroAlmacen(@RequestBody AlmacenRequest request){
        return new ResponseEntity<>(almacenService.registrarAlmacen(request) , HttpStatus.OK);
    }

    @PostMapping("/registrarSucursal")
    public ResponseEntity<?> registroSucursal(@RequestBody SucursalRequest request){
        return new ResponseEntity<>(sucursalService.regitrarSucursal(request), HttpStatus.OK);
    }


    @GetMapping("/allSucursal")
    public ResponseEntity<List<SucursalResponse>> allSucursal(){
        List<SucursalResponse> allBarrios = sucursalService.listAllSucursal();
        return new ResponseEntity<>(allBarrios, HttpStatus.OK);
    }

    @GetMapping("/allAlmacen")
    public ResponseEntity<List<AlmacenResponse>> listAllAlmacen() {
        List<AlmacenResponse> allAlmacenes = almacenService.listAllAlmacen();
        return new ResponseEntity<List<AlmacenResponse>>(allAlmacenes, HttpStatus.OK);
    }


    @PutMapping("/updateSucursal")
    public ResponseEntity<?> updateSucursal(@RequestBody SucursalRequest sucursalRequest){
        sucursalService.actualizardatosSucursal(sucursalRequest);

        return new ResponseEntity(new Mensaje("Sucursal Actualizado"), HttpStatus.OK);
    }

    @PutMapping("/updateAlmacen")
    public ResponseEntity<?> updateAlmacen(@RequestBody AlmacenRequest almacenRequest ) {
        almacenService.actualizarAlmacenconid(almacenRequest);
        return new ResponseEntity(new Mensaje("Almacen Actualizado"), HttpStatus.OK);
    }

}
