package com.portfolio.fdp.Service;

import com.portfolio.fdp.Entity.Experience;
import com.portfolio.fdp.Repository.RExperience;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SExperience {

    @Autowired
    RExperience rExpe;

    public List<Experience> list() {
        return rExpe.findAll();
    }

    public Optional<Experience> getOne(int id) {
        return rExpe.findById(id);
    }

    public void save(Experience experiencia) {
        rExpe.save(experiencia);
    }

    public void delete(int id) {
        rExpe.deleteById(id);
    }

    public boolean existsById(int id) {
        return rExpe.existsById(id);
    }

}
