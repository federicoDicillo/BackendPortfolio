package com.portfolio.fdp.Service;

import com.portfolio.fdp.Entity.Skills;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.fdp.Repository.RSkills;

@Service
@Transactional
public class SSkills {

    @Autowired
    RSkills rSkills;

    public List<Skills> list() {
        return rSkills.findAll();
    }

    public Optional<Skills> getOne(int id) {
        return rSkills.findById(id);
    }

    public Optional<Skills> getBySkill(String skill) {
        return rSkills.findByUnaSkill(skill);
    }

    public void save(Skills skills) {
        rSkills.save(skills);
    }

    public void delete(int id) {
        rSkills.deleteById(id);
    }

    public boolean existsById(int id) {
        return rSkills.existsById(id);
    }

    public boolean existsByUnaSkill(String skill) {
        return rSkills.existsByUnaSkill(skill);
    }
}
