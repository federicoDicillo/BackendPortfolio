
package com.portfolio.fdp.Controller;

import com.portfolio.fdp.Dto.dtoExperience;
import com.portfolio.fdp.Entity.Experience;
import com.portfolio.fdp.Security.Controller.Mensaje;
import com.portfolio.fdp.Service.SExperience;
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
@CrossOrigin(origins = "https://portfoliofd-ap.web.app/")
@RequestMapping({"explab"})
public class CExperience {
    @Autowired SExperience sExpe;
    
   
    @GetMapping("/listar")
    public ResponseEntity<List<Experience>> list() {
        List<Experience> list = sExpe.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") int id) {
        if (!sExpe.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        Experience experiencia = sExpe.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperience dtoexpe) {
        if (StringUtils.isBlank(dtoexpe.getPuesto())) {
            return new ResponseEntity(new Mensaje("Es obligatorio ingresar un campo"), HttpStatus.BAD_REQUEST);
        }
        
        Experience experiencia = new Experience(dtoexpe.getPuesto(), dtoexpe.getEmpresa(),
                dtoexpe.getTiempo());
        sExpe.save(experiencia);

        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoexpe) {
        //valida si existe el id
        if (!sExpe.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }
        //el campo no puede estar vacio
        if (StringUtils.isBlank(dtoexpe.getPuesto())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //si pasa validaciones recien aca actualiza el objeto
         Experience experiencia = sExpe.getOne(id).get();
        experiencia.setPuesto(dtoexpe.getPuesto());
        experiencia.setEmpresa(dtoexpe.getEmpresa());
        experiencia.setTiempo(dtoexpe.getTiempo());

        sExpe.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //valida si existe el id
        if (!sExpe.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }

        sExpe.delete(id);

        return new ResponseEntity(new Mensaje("Experiencia eliminado"), HttpStatus.OK);
    }
}
