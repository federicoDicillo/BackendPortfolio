package com.portfolio.fdp.Controller;

import com.portfolio.fdp.Dto.dtoSkills;
import com.portfolio.fdp.Entity.Skills;
import com.portfolio.fdp.Security.Controller.Mensaje;
import com.portfolio.fdp.Service.SSkills;
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
@RequestMapping({"skills"})
@CrossOrigin(origins = "https://portfoliofd-ap.web.app/")
public class CSkills {

    @Autowired
    SSkills sSkills;

    @GetMapping("/listar")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = sSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!sSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        Skills skill = sSkills.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoskill) {
        if (StringUtils.isBlank(dtoskill.getUnaSkill())) {
            return new ResponseEntity(new Mensaje("Es obligatorio ingresar un campo"), HttpStatus.BAD_REQUEST);
        }
        if (sSkills.existsByUnaSkill(dtoskill.getUnaSkill())) {
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }
        Skills skill = new Skills(dtoskill.getUnaSkill());
        sSkills.save(skill);

        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtoskill) {
        //valida si existe el id
        if (!sSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }
        //compara nombres de habilidades
        if (sSkills.existsByUnaSkill(dtoskill.getUnaSkill()) && id != sSkills.getBySkill(dtoskill.getUnaSkill()).get().getIdSkill()) {
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }
        //el campo no puede estar vacio
        if (StringUtils.isBlank(dtoskill.getUnaSkill())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //si pasa validaciones recien aca actualiza el objeto
        Skills skill = sSkills.getOne(id).get();
        skill.setUnaSkill(dtoskill.getUnaSkill());

        sSkills.save(skill);
        return new ResponseEntity(new Mensaje("Habilidad actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //valida si existe el id
        if (!sSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }

        sSkills.delete(id);

        return new ResponseEntity(new Mensaje("Idioma eliminado"), HttpStatus.OK);
    }

}
