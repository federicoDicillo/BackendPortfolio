
package com.portfolio.fdp.Repository;

import com.portfolio.fdp.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPersona extends JpaRepository<Persona, Integer>{
    
}
