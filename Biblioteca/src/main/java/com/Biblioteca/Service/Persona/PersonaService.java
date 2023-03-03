package com.Biblioteca.Service.Persona;

import com.Biblioteca.DTO.Persona.*;
import com.Biblioteca.Exceptions.BadRequestException;
import com.Biblioteca.Models.Persona.Proveedor;
import com.Biblioteca.Models.Ubicacion.Cuidad;
import com.Biblioteca.Models.Empresa.Sucursal;
import com.Biblioteca.Models.Persona.Cliente;
import com.Biblioteca.Models.Persona.Persona;
import com.Biblioteca.Models.Persona.Usuario;
import com.Biblioteca.Models.Roles.Roles;

import com.Biblioteca.Models.Ubicacion.Pais;
import com.Biblioteca.Repository.Persona.ProveedorRepository;
import com.Biblioteca.Repository.Ubicacion.CuidadRepository;
import com.Biblioteca.Repository.Persona.ClienteRepository;
import com.Biblioteca.Repository.Persona.PersonaRepository;
import com.Biblioteca.Repository.Persona.UsuarioRepository;
import com.Biblioteca.Repository.Empresa.SucursalRepository;
import com.Biblioteca.Repository.RolesRepository;

import com.Biblioteca.Repository.Ubicacion.PaisRepository;
import com.Biblioteca.Security.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonaService implements UserDetailsService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private CuidadRepository cuidadRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;



    @Transactional
    public PersonaUsuarioResponse registrarUsuario(PersonaUsuarioRequest personaUsuarioRequest) throws Exception {
        Optional<Persona> optionalPersona = personaRepository.findByEmail(personaUsuarioRequest.getEmail());
        if(!optionalPersona.isPresent()) {
            Persona newPersona = new Persona();
            newPersona.setCedula(personaUsuarioRequest.getCedula());
            newPersona.setApellidos(personaUsuarioRequest.getApellidos());
            newPersona.setNombres(personaUsuarioRequest.getNombres());
            newPersona.setTelefono(personaUsuarioRequest.getTelefono());
            newPersona.setEmail(personaUsuarioRequest.getEmail());
            newPersona.setDireccion(personaUsuarioRequest.getDireccion());
            newPersona.setFechaNacimiento(personaUsuarioRequest.getFechaNacimiento());

            if (!getPersona(personaUsuarioRequest.getCedula())) {
                Persona persona = personaRepository.save(newPersona);
                if (persona != null) {
                    guardarUsuario(persona.getCedula(), personaUsuarioRequest.getClave(), personaUsuarioRequest.getIdRol() , personaUsuarioRequest.getIdSucursal());
                    Optional<Usuario> user = usuarioRepository.findByPersona(persona);
                    return new PersonaUsuarioResponse(persona.getId(), persona.getCedula(),
                            persona.getApellidos(), persona.getNombres(), persona.getEmail(),
                             persona.getTelefono(), user.get().getClave(), persona.getDireccion() , persona.getFechaNacimiento(), user.get().getRoles().getId(), user.get().getSucursal().getId(),generateTokenSignUp(personaUsuarioRequest));

                }else {
                    log.error("No se puedo guardar el usuario con cédula: {} e email: {}", personaUsuarioRequest.getCedula(), personaUsuarioRequest.getEmail());
                    throw new BadRequestException("No se pudo guardar el usuario");
                }
            }else {
                log.error("La cédula ya está registrada: {}", personaUsuarioRequest.getCedula());
                throw new BadRequestException("La cedula ingresada, ya esta registrada, si la cedula le pertenece contactenos a");
            }
        }else {
            throw new BadRequestException("El email ingresado, ya esta registrado");
        }
    }


    public boolean updateUsuario(PersonaUsuarioRequest personaUsuarioRequest){
        Optional<Persona> optionalPersona = personaRepository.findById(personaUsuarioRequest.getId());
        if(optionalPersona.isPresent()) {

            optionalPersona.get().setCedula(personaUsuarioRequest.getCedula());
            optionalPersona.get().setApellidos(personaUsuarioRequest.getApellidos());
            optionalPersona.get().setNombres(personaUsuarioRequest.getNombres());
            optionalPersona.get().setTelefono(personaUsuarioRequest.getTelefono());
            optionalPersona.get().setEmail( personaUsuarioRequest.getEmail());
            optionalPersona.get().setDireccion(personaUsuarioRequest.getDireccion());
            optionalPersona.get().setFechaNacimiento(personaUsuarioRequest.getFechaNacimiento());
            try{
                Persona persona = personaRepository.save(optionalPersona.get());
                if(persona != null){
                   actualizarUsuario(persona, personaUsuarioRequest.getClave(), personaUsuarioRequest.getIdRol() ,personaUsuarioRequest.getIdSucursal());

                }else {
                    throw new BadRequestException("No se actualizó la persona");
                }
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizó la persona" + ex);
            }
        }else{
            throw new BadRequestException("No existe una persona con id" + personaUsuarioRequest.getId());
        }
        return false;
    }
    private boolean guardarUsuario(String cedula,String clave,Long idRol , Long idSucursal){
        Optional<Persona> optionalPersona = personaRepository.findByCedula(cedula);
        if(optionalPersona.isPresent()){

            Optional<Roles> optionalRoles= rolesRepository.findById(idRol);
            if(optionalRoles.isPresent()){

                Optional<Sucursal> optionalSucursal = sucursalRepository.findById(idSucursal);
                if (optionalSucursal.isPresent()){

                    Persona persona = optionalPersona.get();
                    Usuario newUsuario = new Usuario();
                    newUsuario.setClave(clave);
                    newUsuario.setPersona(persona);
                    newUsuario.setRoles(optionalRoles.get());
                    newUsuario.setSucursal(optionalSucursal.get());

                    Usuario user = usuarioRepository.save(newUsuario);
                    if(user!=null){
                        return true;
                    }else{
                        throw new BadRequestException("Usuario no registrado");
                    }

                }else{
                    throw new BadRequestException("La sucursal seleccionado no existe");
                }



            }else{
                throw new BadRequestException("El rol seleccionado no existe");
            }

        }else{
            throw new BadRequestException("La cedula ingresada, no está registrada");
        }
    }
    private boolean actualizarUsuario(Persona persona, String clave,Long idRol , Long idSucursal){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByPersona(persona);
        if(optionalUsuario.isPresent()){
            Optional<Roles> optionalRoles= rolesRepository.findById(idRol);
            if(optionalRoles.isPresent()){

                Optional<Sucursal> optionalSucursales = sucursalRepository.findById(idSucursal);

                if(optionalSucursales.isPresent()){
                    optionalUsuario.get().setClave(clave);
                    optionalUsuario.get().setPersona(persona);
                    optionalUsuario.get().setRoles(optionalRoles.get());
                    //optionalUsuario.get().setSucursal(optionalSucursales.get());
                    try{
                        Usuario usuario = usuarioRepository.save(optionalUsuario.get());
                        return true;
                    }catch (Exception ex) {
                        throw new BadRequestException("No se actualizó tbl_usuario" + ex);
                    }
                }else{
                    throw new BadRequestException("Sucursal seleccionado no existe");
                }



            }else{
                throw new BadRequestException("El rol seleccionado no existe");
            }

        }else{
            throw new BadRequestException("La cedula ingresada, no está registrada");
        }
    }


    private boolean getPersona(String cedula) {
        return personaRepository.existsByCedula(cedula);
    }



    public List<PersonaUsuarioResponse> listAllUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioRequest->{
            PersonaUsuarioResponse pcr = new PersonaUsuarioResponse();
            pcr.setId(usuarioRequest.getPersona().getId());
            pcr.setIdUsuario(usuarioRequest.getId());
            pcr.setCedula(usuarioRequest.getPersona().getCedula());
            pcr.setNombres(usuarioRequest.getPersona().getNombres());
            pcr.setApellidos(usuarioRequest.getPersona().getApellidos());
            pcr.setTelefono(usuarioRequest.getPersona().getTelefono());
            pcr.setEmail(usuarioRequest.getPersona().getEmail());
            pcr.setIdRol(usuarioRequest.getRoles().getId());
            pcr.setNombreRol(usuarioRequest.getRoles().getDescripcion());
            pcr.setIdSucursal(usuarioRequest.getSucursal().getId());
            pcr.setNombreSucursal(usuarioRequest.getSucursal().getNombre());
            pcr.setFechaNacimiento(usuarioRequest.getPersona().getFechaNacimiento());
            pcr.setDireccion(usuarioRequest.getPersona().getDireccion());


            return pcr;
        }).collect(Collectors.toList());
    }

    public PersonaUsuarioResponse usuarioByCedula(String cedula){
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
                throw new BadRequestException("No existe un persona con cédula" +cedula);
            }
        }else{
            throw new BadRequestException("No existe un cliente vinculado a esa persona");
        }
    }



    public PersonaUsuarioResponse login (UsuarioRequest usuarioRequest) throws Exception {
        Optional<Persona> optional = personaRepository.findByCedula(usuarioRequest.getCedula());
        if(optional.isPresent()){
            Optional<Usuario> usuarioOptional= usuarioRepository.findByPersona(optional.get());
            if(usuarioOptional.isPresent()){
                if(usuarioRequest.getClave().equals(usuarioOptional.get().getClave())){
                    return new PersonaUsuarioResponse(optional.get().getId(),optional.get().getCedula(),
                            optional.get().getApellidos(), optional.get().getNombres(), optional.get().getEmail(),
                            optional.get().getTelefono(), usuarioOptional.get().getClave(), usuarioOptional.get().getRoles().getId(),
                            generateTokenLogin(usuarioRequest));
                }else{
                    throw new BadRequestException("Contraseña incorrecta para email: " + usuarioRequest.getCedula());
                }
            }else{
                log.info("CEDULA NO EXISTE");
                throw new BadRequestException("Usuario no registrado como usuario");
            }
        }else{
            log.info("CEDULA NO EXISTE");
            throw new BadRequestException("Usuario no registrado");
        }
    }
        @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Persona> usuario = personaRepository.findByCedula(email);
        return new org.springframework.security.core.userdetails.User(usuario.get().getCedula(), usuario.get().getCedula(), new ArrayList<>());
    }

    public String generateTokenLogin(UsuarioRequest userRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getCedula(), userRequest.getCedula())
            );
        } catch (Exception ex) {
            log.error("INVALID: error al generar token en login de usuario con cedula: {}", userRequest.getCedula());
            throw new Exception("INAVALID");
        }
        return jwtUtil.generateToken(userRequest.getCedula());
    }

    public String generateTokenSignUp(PersonaUsuarioRequest registerRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(registerRequest.getCedula(), registerRequest.getCedula())
            );
        } catch (Exception ex) {
            log.error("INVALID: error al generar token en signup de usuario con cedula: {}", registerRequest.getCedula());
            throw new BadRequestException("INAVALID");
        }
        return jwtUtil.generateToken(registerRequest.getCedula());
    }



    //////////////////////////////////////////// CLIENTE

    @Transactional
    public PersonaClienteResponse registrarCliente(PersonaClienteRequest personaClienteRequest) throws Exception {
        Optional<Persona> optionalPersona = personaRepository.findByCedula(personaClienteRequest.getCedula());

        if(!optionalPersona.isPresent()) {
            Persona newPersona = new Persona();
            newPersona.setCedula(personaClienteRequest.getCedula());
            newPersona.setApellidos(personaClienteRequest.getApellidos());
            newPersona.setNombres(personaClienteRequest.getNombres());
            newPersona.setTelefono(personaClienteRequest.getTelefono());
            newPersona.setEmail(personaClienteRequest.getEmail());
            newPersona.setDireccion(personaClienteRequest.getDireccion());
            newPersona.setFechaNacimiento(personaClienteRequest.getFechaNacimiento());

            if (!getPersona(personaClienteRequest.getCedula())) {
                Persona persona = personaRepository.save(newPersona);
                if (persona != null) {

                    guardarCliente(persona.getCedula() , personaClienteRequest.getIdCuidad());


                    Optional<Cliente> cliente = clienteRepository.findByPersona(persona);
                    return new PersonaClienteResponse(persona.getId(), persona.getCedula(),
                            persona.getApellidos(), persona.getNombres(), persona.getEmail(),
                            persona.getTelefono(), persona.getDireccion(), persona.getFechaNacimiento());

                }else {
                    log.error("No se puedo guardar el cliente con cédula: {} e email: {}", personaClienteRequest.getCedula(), personaClienteRequest.getEmail());
                    throw new BadRequestException("No se pudo guardar el usuario");
                }
            }else {
                log.error("La cédula ya está registrada: {}", personaClienteRequest.getCedula());
                throw new BadRequestException("La cedula ingresada, ya esta registrada, si la cedula le pertenece contactenos a");
            }
        }else {
            throw new BadRequestException("La cédula ingresada, ya esta registrado");
        }
    }

    private boolean guardarCliente(String cedula , Long idCuidad){

        Optional<Persona> optionalPersona = personaRepository.findByCedula(cedula);
        if(optionalPersona.isPresent()){
            Persona persona = optionalPersona.get();
            Optional<Cuidad> optionalCuidad = cuidadRepository.findById(idCuidad);

            if (optionalPersona.isPresent()){
                Cuidad cuidad = optionalCuidad.get();


                Cliente newCliente = new Cliente();
                newCliente.setPersona(persona);
                newCliente.setCuidad(cuidad);
                Cliente cliente = clienteRepository.save(newCliente);

                if(cliente!=null){
                    return true;
                }else{
                    throw new BadRequestException("Cliente no registrado");
                }

            }else{
                throw new BadRequestException("no existe una cuidad con el id ingresado");
            }






        }else{
            throw new BadRequestException("La cedula ingresada, no está registrada");
        }
    }


    public List<PersonaClienteResponse> listAllClientes(){
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(clienteRequest ->{

            PersonaClienteResponse pcr = new PersonaClienteResponse();
            pcr.setId(clienteRequest.getPersona().getId());
            pcr.setCedula(clienteRequest.getPersona().getCedula());
            pcr.setNombres(clienteRequest.getPersona().getNombres());
            pcr.setApellidos(clienteRequest.getPersona().getApellidos());
            pcr.setTelefono(clienteRequest.getPersona().getTelefono());
            pcr.setEmail(clienteRequest.getPersona().getEmail());
            pcr.setFechaNacimiento(clienteRequest.getPersona().getFechaNacimiento());
            pcr.setDireccion(clienteRequest.getPersona().getDireccion());
            pcr.setIdCuidad(clienteRequest.getCuidad().getId());
            pcr.setNombreCuidad(clienteRequest.getCuidad().getNombre());

            return pcr;
        }).collect(Collectors.toList());
    }


    public boolean updateCliente(PersonaClienteRequest personaClienteRequest){
        Optional<Persona> optionalPersona = personaRepository.findById(personaClienteRequest.getId());
        if(optionalPersona.isPresent()) {

            optionalPersona.get().setCedula(personaClienteRequest.getCedula());
            optionalPersona.get().setApellidos(personaClienteRequest.getApellidos());
            optionalPersona.get().setNombres(personaClienteRequest.getNombres());
            optionalPersona.get().setTelefono(personaClienteRequest.getTelefono());
            optionalPersona.get().setEmail( personaClienteRequest.getEmail());
            optionalPersona.get().setDireccion(personaClienteRequest.getDireccion());
            optionalPersona.get().setFechaNacimiento(personaClienteRequest.getFechaNacimiento());
            try{
                Persona persona = personaRepository.save(optionalPersona.get());
                if(persona != null){
                    actualizarUsuario(persona , personaClienteRequest.getIdCuidad());

                }else {
                    throw new BadRequestException("No se actualizó la persona");
                }
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizó la persona" + ex);
            }
        }else{
            throw new BadRequestException("No existe una persona con id" + personaClienteRequest.getId());
        }
        return false;
    }

    private boolean actualizarUsuario(Persona persona , Long idCuidad){
        Optional<Cliente> optionalCliente = clienteRepository.findByPersona(persona);
        if(optionalCliente.isPresent()){
            optionalCliente.get().setPersona(persona);

            Optional<Cuidad> optionalCuidad = cuidadRepository.findById(idCuidad);
            Cuidad cuidad = optionalCuidad.get();

            if(optionalCuidad.isPresent()){
            optionalCliente.get().setCuidad(cuidad);

                try{

                    Cliente cliente = clienteRepository.save(optionalCliente.get());
                    return true;
                }catch (Exception ex) {
                    throw new BadRequestException("No se actualizó tbl_cliente" + ex);
                }

            }else{
                throw new BadRequestException("La cuidad ingresada, no está registrada");
            }






        }else{
            throw new BadRequestException("La cedula ingresada, no está registrada");
        }
    }




    //////////////////////////////////////////// PROVEEDOR

    @Transactional
    public PersonaProveedorResponse registrarProveedor( PersonaProveedorRequest personaProveedorRequest) throws Exception {
        Optional<Persona> optionalPersona = personaRepository.findByCedula(personaProveedorRequest.getCedula());

        if(!optionalPersona.isPresent()) {
            Persona newPersona = new Persona();
            newPersona.setCedula(personaProveedorRequest.getCedula());
            newPersona.setApellidos(personaProveedorRequest.getApellidos());
            newPersona.setNombres(personaProveedorRequest.getNombres());
            newPersona.setTelefono(personaProveedorRequest.getTelefono());
            newPersona.setEmail(personaProveedorRequest.getEmail());
            newPersona.setDireccion(personaProveedorRequest.getDireccion());
            newPersona.setFechaNacimiento(personaProveedorRequest.getFechaNacimiento());

            if (!getPersona(personaProveedorRequest.getCedula())) {
                Persona persona = personaRepository.save(newPersona);
                if (persona != null) {

                    guardarProveedor(persona.getCedula() , personaProveedorRequest.getIdPais() , personaProveedorRequest.getNombreBanco(), personaProveedorRequest.getNumeroCuenta() , personaProveedorRequest.getNombreCuidad() );


                    Optional<Proveedor> proveedor = proveedorRepository.findByPersona(persona);

                    return new PersonaProveedorResponse();
                    /*
                    return new PersonaClienteResponse(persona.getId(), persona.getCedula(),
                            persona.getApellidos(), persona.getNombres(), persona.getEmail(),
                            persona.getTelefono(), persona.getDireccion(), persona.getFechaNacimiento());*/

                }else {
                    log.error("No se puedo guardar el proveedor con cédula: {}", personaProveedorRequest.getCedula());
                    throw new BadRequestException("No se pudo guardar el usuario");
                }
            }else {
                log.error("La cédula ya está registrada: {}", personaProveedorRequest.getCedula());
                throw new BadRequestException("La cedula ingresada, ya esta registrada, si la cedula le pertenece contactenos a");
            }
        }else {
            throw new BadRequestException("La cédula ingresada, ya esta registrado");
        }
    }

    private boolean guardarProveedor(String cedula , Long idPais , String nombreBanco ,String numeroCuenta  , String nombreCuidad){

        Optional<Persona> optionalPersona = personaRepository.findByCedula(cedula);
        if(optionalPersona.isPresent()){
            Persona persona = optionalPersona.get();
            Optional<Pais> optionalPais =  paisRepository.findById(idPais);

            if (optionalPersona.isPresent()){
                Pais pais = optionalPais.get();

                Proveedor newProveedor = new Proveedor();
                newProveedor.setPersona(persona);
                newProveedor.setPais(pais);
                newProveedor.setNombrebanco(nombreBanco);
                newProveedor.setNumeroCuenta(numeroCuenta);
                newProveedor.setNombreCuidad(nombreCuidad);
                Proveedor proveedor = proveedorRepository.save(newProveedor);


                if(proveedor!=null){
                    return true;
                }else{
                    throw new BadRequestException("Proveedor no registrado");
                }

            }else{
                throw new BadRequestException("no existe un pais con el id ingresado");
            }

        }else{
            throw new BadRequestException("La cedula ingresada, no está registrada");
        }
    }



    public List<PersonaProveedorResponse> listAllProveedor(){

        List<Proveedor> proveedors = proveedorRepository.findAll();


        return proveedors.stream().map(proveedorRequest ->{

            PersonaProveedorResponse pcr = new PersonaProveedorResponse();


            pcr.setId(proveedorRequest.getPersona().getId());
            pcr.setIdProveedor(proveedorRequest.getId());
            pcr.setCedula(proveedorRequest.getPersona().getCedula());
            pcr.setNombres(proveedorRequest.getPersona().getNombres());
            pcr.setApellidos(proveedorRequest.getPersona().getApellidos());
            pcr.setTelefono(proveedorRequest.getPersona().getTelefono());
            pcr.setEmail(proveedorRequest.getPersona().getEmail());
            pcr.setFechaNacimiento(proveedorRequest.getPersona().getFechaNacimiento());
            pcr.setDireccion(proveedorRequest.getPersona().getDireccion());
            pcr.setNombreBanco(proveedorRequest.getNombrebanco());
            pcr.setNumeroCuenta(proveedorRequest.getNumeroCuenta());
            pcr.setIdPais(proveedorRequest.getPais().getId());
            pcr.setNombrePais(proveedorRequest.getPais().getNombre());
            pcr.setNombreCuidad(proveedorRequest.getNombreCuidad());

            return pcr;
        }).collect(Collectors.toList());
    }


    public PersonaProveedorResponse proveedorByCedula(String cedula){
        PersonaProveedorResponse response = new PersonaProveedorResponse();

        Optional<Persona> persona = personaRepository.findByCedula(cedula);

        if(persona.isPresent()) {
            Optional<Proveedor> proveedor = proveedorRepository.findByPersona(persona.get());

            if(proveedor.isPresent()) {
                response.setId(persona.get().getId());

                response.setId(persona.get().getId());
                response.setIdProveedor(proveedor.get().getId());
                response.setCedula(persona.get().getCedula());
                response.setNombres(persona.get().getNombres());
                response.setApellidos(persona.get().getApellidos());
                response.setTelefono(persona.get().getTelefono());
                response.setEmail(persona.get().getEmail());
                response.setFechaNacimiento(persona.get().getFechaNacimiento());
                response.setDireccion(persona.get().getDireccion());
                response.setNombreBanco(proveedor.get().getNombrebanco());
                response.setNumeroCuenta(proveedor.get().getNumeroCuenta());
                response.setIdPais(proveedor.get().getPais().getId());
                response.setNombrePais(proveedor.get().getPais().getNombre());
                response.setNombreCuidad(proveedor.get().getNombreCuidad());

                return response;
            }else{
                throw new BadRequestException("No existe un persona con cédula" +cedula);
            }
        }else{
            throw new BadRequestException("No existe un proveedor vinculado a esa persona");
        }
    }



    public boolean updateProveedor(PersonaProveedorRequest personaProveedorRequest){
        Optional<Persona> optionalPersona = personaRepository.findById(personaProveedorRequest.getId());
        if(optionalPersona.isPresent()) {

            optionalPersona.get().setCedula(personaProveedorRequest.getCedula());
            optionalPersona.get().setApellidos(personaProveedorRequest.getApellidos());
            optionalPersona.get().setNombres(personaProveedorRequest.getNombres());
            optionalPersona.get().setTelefono(personaProveedorRequest.getTelefono());
            optionalPersona.get().setEmail( personaProveedorRequest.getEmail());
            optionalPersona.get().setDireccion(personaProveedorRequest.getDireccion());
            optionalPersona.get().setFechaNacimiento(personaProveedorRequest.getFechaNacimiento());
            try{
                Persona persona = personaRepository.save(optionalPersona.get());
                if(persona != null){
                    actualizarProveedor(persona , personaProveedorRequest.getIdPais() , personaProveedorRequest.getNombreBanco(), personaProveedorRequest.getNumeroCuenta() , personaProveedorRequest.getNombreCuidad() );

                }else {
                    throw new BadRequestException("No se actualizó la persona");
                }
            }catch (Exception ex) {
                throw new BadRequestException("No se actualizó la persona" + ex);
            }
        }else{
            throw new BadRequestException("No existe una persona con id" + personaProveedorRequest.getId());
        }
        return false;
    }

    private boolean actualizarProveedor(Persona persona , Long idPais , String nombreBanco ,String numeroCuenta  , String nombreCuidad){

        Optional<Proveedor> optionalProveedor = proveedorRepository.findByPersona(persona);
        if(optionalProveedor.isPresent()){
            optionalProveedor.get().setPersona(persona);

            Optional<Pais> optionalPais =  paisRepository.findById(idPais);
            Pais pais = optionalPais.get();


            if(optionalPais.isPresent()){
                optionalProveedor.get().setPais(pais);
                optionalProveedor.get().setNombrebanco(nombreBanco);
                optionalProveedor.get().setNumeroCuenta(numeroCuenta);
                optionalProveedor.get().setNombreCuidad(nombreCuidad);

                try{

                    Proveedor proveedor = proveedorRepository.save(optionalProveedor.get());

                    return true;
                }catch (Exception ex) {
                    throw new BadRequestException("No se actualizó tbl_proveedor" + ex);
                }

            }else{
                throw new BadRequestException("La cuidad ingresada, no está registrada");
            }






        }else{
            throw new BadRequestException("La cedula ingresada, no está registrada");
        }
    }
}
