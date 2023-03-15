
package com.portfolio.fdp.Controller;

import com.portfolio.fdp.Dto.dtoProyects;
import com.portfolio.fdp.Entity.Proyect;
import com.portfolio.fdp.Security.Controller.Mensaje;
import com.portfolio.fdp.Service.SProyects;
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
@RequestMapping({"proyectos"})
public class CProyects {
   @Autowired SProyects sProyects;
   
   
    @GetMapping("/listar")
    public ResponseEntity<List<Proyect>> list() {
        List<Proyect> list = sProyects.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyect> getById(@PathVariable("id") int id) {
        if (!sProyects.existsByid(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        Proyect proyecto = sProyects.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyects dtoproyects) {
        if (StringUtils.isBlank(dtoproyects.getNameProy())) {
            return new ResponseEntity(new Mensaje("Es obligatorio ingresar un campo"), HttpStatus.BAD_REQUEST);
        }
        if (sProyects.existsByNameProy(dtoproyects.getNameProy())) {
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        Proyect proyecto = new Proyect(dtoproyects.getNameProy(), dtoproyects.getDetailsProy());
        sProyects.save(proyecto);

        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyects dtoproyects) {
        //valida si existe el id
        if (!sProyects.existsByid(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }
        //compara nombres de habilidades
        if (sProyects.existsByNameProy(dtoproyects.getNameProy()) && id != sProyects.getByNameProy(dtoproyects.getNameProy()).get().getIdProy()) {
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        //el campo no puede estar vacio
        if (StringUtils.isBlank(dtoproyects.getNameProy())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //si pasa validaciones recien aca actualiza el objeto
        Proyect proyecto = sProyects.getOne(id).get();
        proyecto.setNameProy(dtoproyects.getNameProy());
        proyecto.setDetailsProy(dtoproyects.getDetailsProy());

        sProyects.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //valida si existe el id
        if (!sProyects.existsByid(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }

        sProyects.delete(id);

        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
    
  
   
}
