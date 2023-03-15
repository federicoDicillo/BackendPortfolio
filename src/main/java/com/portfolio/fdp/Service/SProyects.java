package com.portfolio.fdp.Service;

import com.portfolio.fdp.Entity.Proyect;
import com.portfolio.fdp.Repository.RProyects;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProyects {

    @Autowired
    RProyects rProyects;

    public List<Proyect> list() {
        return rProyects.findAll();
    }

    public Optional<Proyect> getOne(int id) {
        return rProyects.findById(id);
    }

    public Optional<Proyect> getByNameProy(String nameProy){
        return rProyects.findByNameProy(nameProy);
    }
    
    public void save(Proyect proyecto){
        rProyects.save(proyecto);
    }
    
    public void delete(int id){
        rProyects.deleteById(id);
    }
    
    public boolean existsByid(int id){
        return rProyects.existsById(id);
    }
    
    public boolean existsByNameProy(String nameProy){
        return rProyects.existsByNameProy(nameProy);
    }
}
