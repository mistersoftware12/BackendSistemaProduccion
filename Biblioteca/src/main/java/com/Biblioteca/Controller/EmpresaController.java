package com.Biblioteca.Controller;

import com.Biblioteca.DTO.empresa.sucursales.*;
import com.Biblioteca.Exceptions.Mensaje;
import com.Biblioteca.Service.Empresa.AlmacenService;
import com.Biblioteca.Service.Empresa.BodegaService;
import com.Biblioteca.Service.Empresa.SucursalService;
import com.Biblioteca.Service.Empresa.TallerService;
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

@Autowired
    BodegaService bodegaService;

@Autowired
    TallerService tallerService;

    @PostMapping("/registrarAlmacen")
    public ResponseEntity<?> registroAlmacen(@RequestBody AlmacenRequest request){
        return new ResponseEntity<>(almacenService.registrarAlmacen(request) , HttpStatus.OK);
    }

    @PostMapping("/registrarSucursal")
    public ResponseEntity<?> registroSucursal(@RequestBody SucursalRequest request){
        return new ResponseEntity<>(sucursalService.regitrarSucursal(request), HttpStatus.OK);
    }

    @PostMapping("/registrarBodega")
    public ResponseEntity<?> registroBodega(@RequestBody BodegaRequest request){
        return new ResponseEntity<>( bodegaService.registrarBodega(request), HttpStatus.OK);
    }

    @PostMapping("/registrarTaller")
    public ResponseEntity<?> registroTaller(@RequestBody  TallerRequest request ){
        return new ResponseEntity<>( tallerService.registrarTaller(request), HttpStatus.OK);
    }



    @GetMapping("/allSucursal")
    public ResponseEntity<List<SucursalResponse>> allSucursal(){
        List<SucursalResponse> allSucursal = sucursalService.listAllSucursal();
        return new ResponseEntity<>(allSucursal, HttpStatus.OK);
    }

    @GetMapping("/allAlmacen")
    public ResponseEntity<List<AlmacenResponse>> listAllAlmacen() {
        List<AlmacenResponse> allAlmacenes = almacenService.listAllAlmacen();
        return new ResponseEntity<List<AlmacenResponse>>(allAlmacenes, HttpStatus.OK);
    }

    @GetMapping("/allBodega")
    public  ResponseEntity<List<BodegaResponse>> listAllBodega(){
        List<BodegaResponse> allBodegas = bodegaService.listAllBodega();
      return new ResponseEntity<List<BodegaResponse>>(allBodegas, HttpStatus.OK);
    }

    @GetMapping("/allTaller")
    public  ResponseEntity<List<TallerResponse>> listAllTaller(){
        List<TallerResponse> allTalleres = tallerService.listAllTaller();
        return new ResponseEntity<List<TallerResponse>>(allTalleres, HttpStatus.OK);
    }

    //Por id sucursal

    @GetMapping("/allAlmacenByIdSucursal/{idSucursal}")
    public ResponseEntity<List<AlmacenResponse>> listAllAlmacenByIdSucursal(@PathVariable Long idSucursal) {
        List<AlmacenResponse> allAlmacenes = almacenService.listAllAlmacenPorScucursaById(idSucursal);
        return new ResponseEntity<List<AlmacenResponse>>(allAlmacenes, HttpStatus.OK);
    }

    @GetMapping("/allBodegaBySucursal/{idSucursal}")
    public ResponseEntity<List<BodegaResponse>> listAllAlmacesByIdSucursal(@PathVariable Long idSucursal) {
        List<BodegaResponse> allAlmacenes = bodegaService.listAllBodegaPorScucursaById(idSucursal);
        return new ResponseEntity<List<BodegaResponse>>(allAlmacenes, HttpStatus.OK);
    }

    @GetMapping("/allTalleByIdSucursal/{idSucursal}")
    public ResponseEntity<List<TallerResponse>> listAllAlmacenssByIdSucursal(@PathVariable Long idSucursal) {
        List<TallerResponse> allAlmacenes = tallerService.listAllTallerPorScucursaById(idSucursal);
        return new ResponseEntity<List<TallerResponse>>(allAlmacenes, HttpStatus.OK);
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

    @PutMapping("/updateBodega")
    public ResponseEntity<?> updateBodega(@RequestBody BodegaRequest bodegaRequest ) {
        bodegaService.actualizarBodegaconid(bodegaRequest);

        return new ResponseEntity(new Mensaje("Bodega Actualizado"), HttpStatus.OK);
    }

    @PutMapping("/updateTaller")
    public ResponseEntity<?> updateTaller(@RequestBody TallerRequest tallerRequest ) {
        tallerService.actualizarTallerconid(tallerRequest);
        return new ResponseEntity(new Mensaje("Taller Actualizado"), HttpStatus.OK);
    }

}
