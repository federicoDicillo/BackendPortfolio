package com.portfolio.fdp.Security.Controller;

import com.portfolio.fdp.Security.Dto.JwtDTO;
import com.portfolio.fdp.Security.Dto.LoginUsuario;
import com.portfolio.fdp.Security.Dto.NuevoUsuario;
import com.portfolio.fdp.Security.Entity.Rol;
import com.portfolio.fdp.Security.Entity.Usuario;
import com.portfolio.fdp.Security.Enums.RolNombre;
import com.portfolio.fdp.Security.Service.RolService;
import com.portfolio.fdp.Security.Service.UsuarioService;
import com.portfolio.fdp.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth"})
@CrossOrigin(origins = "https://federicodicilloportfolio.web.app")
public class AuthController {

    @Autowired
    PasswordEncoder passEncoder;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    UsuarioService usuarioServ;
    @Autowired
    RolService rolServ;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mas puestos o email invalido."), HttpStatus.BAD_REQUEST);
        }

        if (usuarioServ.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioServ.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(), passEncoder.encode(nuevoUsuario.getPassword()));

        //Por defecto todos van a tener el Rol user a menos que contenga "admin"
        Set<Rol> roles = new HashSet<>();
        roles.add(rolServ.getByRolNombre(RolNombre.ROLE_USER).get());

        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolServ.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioServ.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario guardado."), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos invalidos"), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generatedToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}
