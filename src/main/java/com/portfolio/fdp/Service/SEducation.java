package com.portfolio.fdp.Service;

import com.portfolio.fdp.Entity.Study;
import com.portfolio.fdp.Repository.REducation;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SEducation {

    @Autowired
    REducation rEducation;

    public List<Study> list() {
        return rEducation.findAll();
    }

    public Optional<Study> getOne(int id) {
        return rEducation.findById(id);
    }

    public Optional<Study> getByTitulo(String titulo) {
        return rEducation.findByTitulo(titulo);
    }

    public void save(Study study) {
        rEducation.save(study);
    }

    public void delete(int id) {
        rEducation.deleteById(id);
    }

    public boolean existsById(int id) {
        return rEducation.existsById(id);
    }

    public boolean existsByTitulo(String titulo) {
        return rEducation.existsByTitulo(titulo);
    }

}
