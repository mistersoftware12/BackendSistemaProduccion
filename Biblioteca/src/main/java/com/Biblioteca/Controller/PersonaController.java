package com.Biblioteca.Controller;


import com.Biblioteca.DTO.Persona.*;
import com.Biblioteca.Exceptions.Mensaje;
import com.Biblioteca.Service.Persona.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;


    @PutMapping("/updateUsuario")
    public ResponseEntity<?> updateUsuario(@RequestBody PersonaUsuarioRequest request) {
        personaService.updateUsuario(request);
        return new ResponseEntity(new Mensaje("Usuario Actualizado"), HttpStatus.OK);
    }


    @PutMapping("/updateCliente")
    public ResponseEntity<?> updateCliente(@RequestBody PersonaClienteRequest request) {
        personaService.updateCliente(request);
        return new ResponseEntity(new Mensaje("Cliente Actualizado"), HttpStatus.OK);
    }

    @PostMapping("/registroUsuario")
    public ResponseEntity<PersonaUsuarioResponse> registroUsuario(@RequestBody PersonaUsuarioRequest personaRequest) throws Exception {
        PersonaUsuarioResponse personaResponse = personaService.registrarUsuario(personaRequest);

        if (personaResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personaResponse, HttpStatus.CREATED);
    }


    @GetMapping("/allUsuarios")
    public ResponseEntity<List<PersonaUsuarioResponse>> listAllUsuarios() {
        List<PersonaUsuarioResponse> users = personaService.listAllUsuarios();
        return new ResponseEntity<List<PersonaUsuarioResponse>>(users, HttpStatus.OK);
    }

    @GetMapping("/allClientes")
    public ResponseEntity<List<PersonaClienteResponse>> listAllCliestes() {
        List<PersonaClienteResponse> cliente = personaService.listAllClientes();
        return  new ResponseEntity<List<PersonaClienteResponse>>(cliente , HttpStatus.OK);


    }


    @GetMapping("/usuario/{cedula}")
    public ResponseEntity<PersonaUsuarioResponse> listUsuarioByCedula(@PathVariable String cedula){
        PersonaUsuarioResponse user = personaService.usuarioByCedula(cedula);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioRequest request) throws Exception {
        PersonaUsuarioResponse response = personaService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    ////////CLIENTE

    @PostMapping("/registroCliente")
    public ResponseEntity<PersonaClienteResponse> registroCliente(@RequestBody PersonaClienteRequest personaRequest) throws Exception {
        PersonaClienteResponse personaResponse = personaService.registrarCliente(personaRequest);

        if (personaResponse == null) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(personaResponse, HttpStatus.CREATED);
    }


    //Proveedro

    @PostMapping("/registroProveedor")
    public ResponseEntity<PersonaProveedorResponse> registroProveedor(@RequestBody PersonaProveedorRequest personaRequest) throws Exception {
        PersonaProveedorResponse personaResponse = personaService.registrarProveedor(personaRequest);
        if (personaResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personaResponse, HttpStatus.CREATED);
    }


    @GetMapping("/allProveedores")
    public ResponseEntity<List<PersonaProveedorResponse>> listAllProveedores() {
        List<PersonaProveedorResponse> proveedor = personaService.listAllProveedor();

        return  new ResponseEntity<List<PersonaProveedorResponse>>(proveedor , HttpStatus.OK);
    }


    @GetMapping("/allproveedorcedula/{cedula}")
    public ResponseEntity<PersonaProveedorResponse> listProveedorByCedula(@PathVariable String cedula){
       PersonaProveedorResponse pro = personaService.proveedorByCedula(cedula);
        return new ResponseEntity<>(pro, HttpStatus.OK);
    }

    @PutMapping("/updateProveedor")
    public ResponseEntity<?> updateProveedor(@RequestBody  PersonaProveedorRequest request) {
        personaService.updateProveedor(request);
        return new ResponseEntity(new Mensaje("Proveedor Actualizado"), HttpStatus.OK);
    }

}
