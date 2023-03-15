
package com.portfolio.fdp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class AboutMe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //campo primary key autogenerado
    private int idAbout;
    
    @NotNull
    private String aboutText;

    public AboutMe() {
    }

    public AboutMe(String aboutText) {
        this.aboutText = aboutText;
    }

    public int getIdAbout() {
        return idAbout;
    }

    public void setIdAbout(int idAbout) {
        this.idAbout = idAbout;
    }

    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }
    
    
    
}
