
package com.portfolio.fdp.Repository;

import com.portfolio.fdp.Entity.Languages;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RLanguages extends JpaRepository<Languages, Integer>{
    
   public Optional<Languages> findByIdioma(String idioma);
   public boolean existsByIdioma(String idioma);
    
}
