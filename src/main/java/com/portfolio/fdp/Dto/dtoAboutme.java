
package com.portfolio.fdp.Dto;

import javax.validation.constraints.NotBlank;


public class dtoAboutme {
    @NotBlank
    private String aboutText;

    public dtoAboutme() {
    }

    public dtoAboutme(String aboutText) {
        this.aboutText = aboutText;
    }

    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }
    
    
    
}
