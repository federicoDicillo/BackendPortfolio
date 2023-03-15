
package com.portfolio.fdp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Languages {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String idioma;
    private String nivelI;

    public Languages() {
    }

    public Languages(String idioma, String nivelI) {
        this.idioma = idioma;
        this.nivelI = nivelI;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNivelI() {
        return nivelI;
    }

    public void setNivelI(String nivelI) {
        this.nivelI = nivelI;
    }
    
    
    
}
