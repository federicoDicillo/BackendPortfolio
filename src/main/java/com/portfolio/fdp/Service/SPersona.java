package com.portfolio.fdp.Service;

import com.portfolio.fdp.Entity.Persona;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.fdp.Repository.RPersona;
import java.util.Optional;

@Service
@Transactional
public class SPersona {

    @Autowired
    RPersona rPersona;

    public List<Persona> list() {
        return rPersona.findAll();
    }

    public Optional<Persona> getOne(int id) {
        return rPersona.findById(id);
    }

    public void save(Persona persona) {
        rPersona.save(persona);
    }

    public void delete(int id) {
        rPersona.deleteById(id);
    }

    public boolean existById(int id) {
        return rPersona.existsById(id);
    }
}
