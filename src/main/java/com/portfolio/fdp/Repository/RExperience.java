
package com.portfolio.fdp.Repository;

import com.portfolio.fdp.Entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RExperience extends JpaRepository<Experience,Integer>{
    
  
}
