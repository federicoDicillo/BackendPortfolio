
package com.portfolio.fdp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Proyect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //campo primary key autogenerado
    private int idProy;
      
    @NotNull
    @Size(max = 80)
    private String nameProy;
    
    @NotNull
    @Size (max = 500)
    private String detailsProy;

    public Proyect() {
    }

    public Proyect(String nameProy, String detailsProy) {
        this.nameProy = nameProy;
        this.detailsProy = detailsProy;
    }

    public int getIdProy() {
        return idProy;
    }

    public void setIdProy(int idProy) {
        this.idProy = idProy;
    }

    public String getNameProy() {
        return nameProy;
    }

    public void setNameProy(String nameProy) {
        this.nameProy = nameProy;
    }

    public String getDetailsProy() {
        return detailsProy;
    }

    public void setDetailsProy(String detailsProy) {
        this.detailsProy = detailsProy;
    }

    
    
}
