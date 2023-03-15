package com.portfolio.fdp.Controller;

import com.portfolio.fdp.Dto.dtoLanguages;
import com.portfolio.fdp.Entity.Languages;
import com.portfolio.fdp.Security.Controller.Mensaje;
import com.portfolio.fdp.Service.SLanguages;
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
@RequestMapping({"idiomas"})
@CrossOrigin(origins = "https://federicodicilloportfolio.web.app/")
public class CLanguages {

    @Autowired
    SLanguages sLanguages;

    @GetMapping("/listar")
    public ResponseEntity<List<Languages>> list() {
        List<Languages> list = sLanguages.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Languages> getById(@PathVariable("id") int id) {
        if (!sLanguages.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Languages language = sLanguages.getOne(id).get();
        return new ResponseEntity(language, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoLanguages dtolang) {
        if (StringUtils.isBlank(dtolang.getIdioma())) {
            return new ResponseEntity(new Mensaje("El idioma es oblicarorio"), HttpStatus.BAD_REQUEST);
        }
        if (sLanguages.existsByIdioma(dtolang.getIdioma())) {
            return new ResponseEntity(new Mensaje("Ese idioma existe"), HttpStatus.BAD_REQUEST);
        }

        Languages language = new Languages(dtolang.getIdioma(), dtolang.getNivelI());
        sLanguages.save(language);

        return new ResponseEntity(new Mensaje("Idioma agregado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoLanguages dtolang) {
        //valida si existe el id
        if (!sLanguages.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }
        //compara nombres de idiomas
        if (sLanguages.existsByIdioma(dtolang.getIdioma()) && sLanguages.getByIdioma(dtolang.getIdioma()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese idioma ya existe"), HttpStatus.BAD_REQUEST);
        }
        //el campo no puede estar vacio
        if (StringUtils.isBlank(dtolang.getIdioma())) {
            return new ResponseEntity(new Mensaje("El idioma es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        //si pasa validaciones recien aca actualiza el objeto
        Languages language = sLanguages.getOne(id).get();
        language.setIdioma(dtolang.getIdioma());
        language.setNivelI((dtolang.getNivelI()));

        sLanguages.save(language);
        return new ResponseEntity(new Mensaje("Idioma actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //valida si existe el id
        if (!sLanguages.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }

        sLanguages.delete(id);

        return new ResponseEntity(new Mensaje("Idioma eliminado"), HttpStatus.OK);
    }

}
