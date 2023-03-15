
package com.portfolio.fdp.Repository;

import com.portfolio.fdp.Entity.Study;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface REducation extends JpaRepository<Study, Integer>{
    
   public Optional<Study> findByTitulo(String titulo);
   public boolean existsByTitulo(String titulo);
}
