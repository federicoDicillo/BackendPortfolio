package com.portfolio.fdp.Service;

import com.portfolio.fdp.Entity.AboutMe;
import com.portfolio.fdp.Repository.RAboutme;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SAboutme {

    @Autowired
    RAboutme rAboutme;

    public List<AboutMe> list() {
        return rAboutme.findAll();
    }

    public Optional<AboutMe> getOne(int id) {
        return rAboutme.findById(id);
    }

    public void save(AboutMe aboutme) {
        rAboutme.save(aboutme);
    }

    public void delete(int id) {
        rAboutme.deleteById(id);
    }

    public boolean existById(int id) {
        return rAboutme.existsById(id);
    }
}
