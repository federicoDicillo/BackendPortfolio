
package com.portfolio.fdp.Repository;

import com.portfolio.fdp.Entity.AboutMe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RAboutme extends JpaRepository<AboutMe, Integer>{
    
}
