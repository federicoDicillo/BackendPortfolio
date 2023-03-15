
package com.portfolio.fdp.Service;

import com.portfolio.fdp.Entity.Languages;
import com.portfolio.fdp.Repository.RLanguages;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SLanguages {
    @Autowired RLanguages rLanguages;
    
    public List<Languages> list(){
        return rLanguages.findAll();
    }
    
    public Optional<Languages> getOne(int id){
        return rLanguages.findById(id);
    }
    
    public Optional<Languages> getByIdioma(String unidioma){
        return rLanguages.findByIdioma(unidioma);
    }
    
    public void save(Languages language){
        rLanguages.save(language);
    }
    
    public void delete(int id){
        rLanguages.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rLanguages.existsById(id);
    }
    
     public boolean existsByIdioma(String idioma){
         return rLanguages.existsByIdioma(idioma);
     }
}
