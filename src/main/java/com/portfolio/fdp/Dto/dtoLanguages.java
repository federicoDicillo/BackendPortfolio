
package com.portfolio.fdp.Dto;

import javax.validation.constraints.NotBlank;


public class dtoLanguages {
    
    @NotBlank
    private String idioma;
    @NotBlank
    private String nivelI;

    public dtoLanguages() {
    }

    public dtoLanguages(String idioma, String nivelI) {
        this.idioma = idioma;
        this.nivelI = nivelI;
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
