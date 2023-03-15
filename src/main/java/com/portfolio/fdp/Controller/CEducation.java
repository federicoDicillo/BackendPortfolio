package com.portfolio.fdp.Controller;

import com.portfolio.fdp.Dto.dtoEducation;
import com.portfolio.fdp.Entity.Study;
import com.portfolio.fdp.Security.Controller.Mensaje;
import com.portfolio.fdp.Service.SEducation;
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
@CrossOrigin(origins = "https://federicodicilloportfolio.web.app/")
@RequestMapping({"estudios"})
public class CEducation {

    @Autowired
    SEducation sEducation;

    @GetMapping("/listar")
    public ResponseEntity<List<Study>> list() {
        List<Study> list = sEducation.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Study> getById(@PathVariable("id") int id) {
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        Study study = sEducation.getOne(id).get();
        return new ResponseEntity(study, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducation dtoeducation) {
        if (StringUtils.isBlank(dtoeducation.getTitulo())) {
            return new ResponseEntity(new Mensaje("Es obligatorio ingresar un campo"), HttpStatus.BAD_REQUEST);
        }
        if (sEducation.existsByTitulo(dtoeducation.getTitulo())) {
            return new ResponseEntity(new Mensaje("Ese estudio ya existe"), HttpStatus.BAD_REQUEST);
        }
        Study study = new Study(dtoeducation.getTitulo(), dtoeducation.getEscuela(), dtoeducation.getNivel(),
                dtoeducation.getTiempo());
        sEducation.save(study);

        return new ResponseEntity(new Mensaje("Estudio agregado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducation dtoeducation) {
        //valida si existe el id
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }
        //compara nombres de habilidades
        if (sEducation.existsByTitulo(dtoeducation.getTitulo()) && id != sEducation.getByTitulo(dtoeducation.getTitulo()).get().getIdStudy()) {
            return new ResponseEntity(new Mensaje("Ese estudio ya existe"), HttpStatus.BAD_REQUEST);
        }
        //el campo no puede estar vacio
        if (StringUtils.isBlank(dtoeducation.getTitulo())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //si pasa validaciones recien aca actualiza el objeto
        Study study = sEducation.getOne(id).get();
        study.setTitulo(dtoeducation.getTitulo());
        study.setEscuela(dtoeducation.getEscuela());
        study.setNivel(dtoeducation.getNivel());
        study.setTiempo(dtoeducation.getTiempo());

        sEducation.save(study);
        return new ResponseEntity(new Mensaje("Estudio actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //valida si existe el id
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }

        sEducation.delete(id);

        return new ResponseEntity(new Mensaje("Estudio eliminado"), HttpStatus.OK);
    }

}
