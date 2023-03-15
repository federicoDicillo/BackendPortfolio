package com.portfolio.fdp.Controller;

import com.portfolio.fdp.Dto.dtoPersona;
import com.portfolio.fdp.Entity.Persona;
import com.portfolio.fdp.Security.Controller.Mensaje;
import com.portfolio.fdp.Service.SPersona;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://portfoliofd-ap.web.app")
@RequestMapping({"/personas"})
public class PersonaController {

    @Autowired
    SPersona sPersona;

    @GetMapping("/listar")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = sPersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!sPersona.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        Persona persona = sPersona.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona) {
        if (StringUtils.isBlank(dtopersona.getName())) {
            return new ResponseEntity(new Mensaje("Es obligatorio ingresar un campo"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = new Persona(dtopersona.getName(), dtopersona.getLastname(), dtopersona.getImgProfile(),
                                    dtopersona.getTituloPer(), 
                                      dtopersona.getEmail(), dtopersona.getPhone());
        sPersona.save(persona);

        return new ResponseEntity(new Mensaje("Creación exitosa"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona) {
        //valida si existe el id
        if (!sPersona.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }
        //el campo no puede estar vacio
        if (StringUtils.isBlank(dtopersona.getName())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //si pasa validaciones recien aca actualiza el objeto
        Persona persona = sPersona.getOne(id).get();
        persona.setName(dtopersona.getName());
        persona.setLastname(dtopersona.getLastname());
        persona.setTituloPer(dtopersona.getTituloPer());
        persona.setEmail(dtopersona.getEmail());
        persona.setPhone(dtopersona.getPhone());
        persona.setImgProfile(dtopersona.getImgProfile());
        
        sPersona.save(persona);
        return new ResponseEntity(new Mensaje("Perfil actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //valida si existe el id
        if (!sPersona.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }

        sPersona.delete(id);

        return new ResponseEntity(new Mensaje("Perfil eliminado"), HttpStatus.OK);
    }

}
