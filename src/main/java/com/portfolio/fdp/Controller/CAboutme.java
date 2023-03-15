
package com.portfolio.fdp.Controller;

import com.portfolio.fdp.Dto.dtoAboutme;
import com.portfolio.fdp.Entity.AboutMe;
import com.portfolio.fdp.Security.Controller.Mensaje;
import com.portfolio.fdp.Service.SAboutme;
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
@RequestMapping({"sobremi"})
@CrossOrigin(origins = "https://federicodicilloportfolio.web.app/")
public class CAboutme {
    @Autowired SAboutme sAboutme;
    
    
    @GetMapping("/listar")
    public ResponseEntity<List<AboutMe>> list() {
        List<AboutMe> list = sAboutme.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<AboutMe> getById(@PathVariable("id") int id) {
        if (!sAboutme.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        AboutMe aboutme = sAboutme.getOne(id).get();
        return new ResponseEntity(aboutme, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoAboutme dtoaboutme) {
        if (StringUtils.isBlank(dtoaboutme.getAboutText())) {
            return new ResponseEntity(new Mensaje("Es obligatorio ingresar un campo"), HttpStatus.BAD_REQUEST);
        }
        
        AboutMe aboutme = new AboutMe(dtoaboutme.getAboutText());
        sAboutme.save(aboutme);

        return new ResponseEntity(new Mensaje("Creación exitosa"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoAboutme dtoaboutme) {
        //valida si existe el id
        if (!sAboutme.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }
        //el campo no puede estar vacio
        if (StringUtils.isBlank(dtoaboutme.getAboutText())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //si pasa validaciones recien aca actualiza el objeto
        AboutMe aboutme= sAboutme.getOne(id).get();
        aboutme.setAboutText(dtoaboutme.getAboutText());

        sAboutme.save(aboutme);
        return new ResponseEntity(new Mensaje("Texto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //valida si existe el id
        if (!sAboutme.existById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }

        sAboutme.delete(id);

        return new ResponseEntity(new Mensaje("Texto eliminado"), HttpStatus.OK);
    }

}
