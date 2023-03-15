package com.portfolio.fdp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Skills {

    public Skills() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //campo primary key autogenerado
    private int idSkill;

    @NotNull
    @Size(min = 1, max = 50)
    private String unaSkill;

    public Skills(String unaSkill) {
        this.unaSkill = unaSkill;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    public String getUnaSkill() {
        return unaSkill;
    }

    public void setUnaSkill(String unaSkill) {
        this.unaSkill = unaSkill;
    }
    


}
